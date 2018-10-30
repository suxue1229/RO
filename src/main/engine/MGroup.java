package engine;

import java.util.*;

public class MGroup {
	// 进水
	public Water streamf;
	// 产水
	public Water streamp;
	// 浓水
	public Water streamc;
	// 膜元件型号
	public String model;
	// 膜面积
	public double moarea;
	// 浓水流通阻力系数 k
	public double k;
	// 透水系数初算值 A
	public double A;
	// 压力容器数
	public int nvi = 1;
	// 内膜元件数
	public int ni = 3;
	// 段间增压 MPa
	public double dpf;
	// 系统膜元件平均压力降
	public double moavgPfc;
	// 系统平均回收率
	public double moavgY;

	// 平均通量 LMH
	public double groupavgFp = 0;

	public List<MBranch> branchs = new ArrayList<>();

	public MGroup(String motype) {
		this.model = motype;
		try {
			this.A = new MBranch(this.model).A;
			this.k = new MBranch(this.model).k;
			this.moarea = new MBranch(this.model).moarea;
		} catch (Exception e) {
		}
	}

	public void groupCalc() throws Exception {
		branchs.clear();
		for (int i = 0; i < ni; i++) {
			branchs.add(new MBranch(this.model));
		}
		for (int i = 0; i < branchs.size(); i++) {
			MBranch branch = branchs.get(i);
			branch.moavgPfc = this.moavgPfc;
			branch.moavgY = this.moavgY;
			if (i == 0) {
				branch.streamf = this.streamf.copy();
				branch.streamf.q = this.streamf.q / this.nvi;
				branch.branchCalc();
			} else if (i > 0) {
				branch.streamf = branchs.get(i - 1).streamc.copy();
				branch.streamf.pf = branchs.get(i - 1).streamf.pf - branchs.get(i - 1).moDropPf();
				branch.streamf.paif(branchs.get(i - 1).streamf.paif() * branchs.get(i).streamf.tds
						/ branchs.get(i - 1).streamf.tds);
				branch.branchCalc();
			}
			this.streamp = branch.streamp.copy();
			this.streamc = branch.streamc.copy();
			this.streamc.q = nvi * branch.streamc.q;
		}
		double temp = 0;
		double tempQp = 0;
		for (MBranch branch : branchs) {
			temp += branch.streamp.q * branch.streamp.tds;
			tempQp += nvi * branch.streamp.q;
		}
		this.streamp.q = tempQp;
		this.streamp.tds = temp / (this.streamp.q / nvi);
		this.streamc.pf = branchs.get(branchs.size() - 1).streamf.pf - branchs.get(branchs.size() - 1).moDropPf();
		this.streamp.pf = this.streamf.pf - this.streamc.pf;
		groupavgFp = (this.streamp.q / nvi) * 1000 / (ni * this.moarea);
	}
}
