package engine;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class MSystem {
	// 系统设计回收率
	private double systemhsl = 0.6;
	// 系统设计产水量 m3/h
	public double systemPQ = 7.5;
	// 膜元件型号
	private String model = "BW_8040";
	// 段间增压 MPa
	public double dpf = 0;
	// 膜面积
	public double moarea;
	// 浓水流通阻力系数 k
	private double k;
	// 透水系数初算值 A
	private double A;
	// 系统实际回收率
	public double systemHSL;
	// 系统脱盐率
	public double systemRs;
	// 系统的平均通量
	public double systemFp;
	// 原水
	public Water streams;
	// 进水
	public Water streamf;
	// 产水
	public Water streamp;
	// 浓水
	public Water streamc;

	public List<MGroup> groups = new ArrayList<>();

	public MSystem() {
		streams = new Water().copy();
		try {
			group(2);
		} catch (Exception e) {
		}
		groups.get(0).nvi = 2;
		groups.get(0).dpf = 0;
		this.A = new MGroup(this.model).A;
		this.k = new MGroup(this.model).k;
		this.moarea = new MGroup(this.model).moarea;
	}

	public void group(int count) throws IllegalArgumentException {
		if (count == 1 || count == 2) {
			while (groups.size() < count) {
				groups.add(new MGroup(this.model));
			}
			while (groups.size() > count) {
				groups.remove(groups.size() - 1);
			}
		} else {
			throw new IllegalArgumentException("膜段数应为1或者2！");
		}
	}

	// 膜型号
	public String motype() {
		return this.model;
	}

	public void motype(String str) throws IllegalArgumentException {
		if (str.equals("BW_8040") || (str.equals("SW_8040")) || (str.equals("ULP_8040"))) {
			this.model = str;
		} else {
			throw new IllegalArgumentException("膜类型应为BW_8040、SW_8040、ULP_8040其中之一！");
		}
	}

	// 系统设计回收率
	public double systemY() {
		return this.systemhsl;
	}

	public void systemY(double val) throws IllegalArgumentException {
		if (val > 0 && val < 1) {
			this.systemhsl = val;
		} else {
			throw new IllegalArgumentException("系统回收率不在(0~1)范围内！");
		}
	}

	// 系统进水量 m3/h
	public double sysFQ() {
		return systemPQ / systemhsl;
	}

	// 串联的内膜元件数
	public int n() {
		int n = 0;
		for (MGroup group : groups) {
			n += group.ni;
		}
		return n;
	}

	// 系统内膜元件数
	public int moN() {
		int num = 0;
		for (MGroup group : groups) {
			num += group.ni * group.nvi;
		}
		return num;
	}

	// 排列比
	private double nvr() {
		return (double) groups.get(groups.size() - 2).nvi / groups.get(groups.size() - 1).nvi;
	}

	// 系统压力降 MPa
	private double sysDpf() {
		double systemDpf = 0;
		double tem;
		if (groups.size() == 1) {
			tem = this.k * groups.get(0).ni * Math.pow(145.038, -1);
			systemDpf = tem * Math.pow(4.403 * systemPQ * (2 - systemhsl) / (2 * systemhsl), 1.7);// 系统压力降
		} else if (groups.size() > 1) {
			tem = (systemPQ * 4.403) * Math.pow(2 * systemhsl * groups.get(groups.size() - 1).nvi, -1);
			systemDpf = Math.pow(3626, -1) * Math.pow(tem * (Math.pow(nvr(), -1) + 1 - systemhsl), 2);// 系统压力降
		}
		return systemDpf;
	}

	// 系统的平均回收率
	private double sysavgY() {
		return 1 - Math.pow((1 - systemhsl), 1.0 / n());
	}

	// 系统的平均浓差极化系数
	private double sysbj() {
		return Math.exp(0.7 * sysavgY());
	}

	// 系统的进水浓度和浓水浓度平均值与进水浓度比值
	private double syscj() {
		return (1 + Math.pow((1 - sysavgY()), -1)) / 2;
	}

	// 系统平均渗透压
	private double sysavgpai() {
		return this.streams.paif() * syscj() * sysbj();
	}

	// 系统进水压力 MPa
	private double sysFPf() {
		return (systemPQ * 4.061) * Math.pow(moN() * this.moarea * this.A * streams.tcf() * streams.ff, -1)
				+ sysDpf() / 2 + sysavgpai();
	}

	public void sysCalc() throws Exception {
		this.streams.q = sysFQ();
		this.streams.pf = sysFPf();
		long start = new Date().getTime();
		while (true) {
			this.streamf = this.streams.copy();
			for (int i = 0; i < groups.size(); i++) {
				MGroup group = groups.get(i);
				group.model = this.model;
				group.moavgPfc = sysDpf() / n();
				group.moavgY = sysavgY();
				if (i == 0) {
					group.streamf = this.streamf.copy();
					group.groupCalc();
				} else {
					group.dpf = this.dpf;
					group.streamf = groups.get(i - 1).streamc.copy();
					group.streamf.pf = groups.get(i - 1).streamc.pf - 0.0372 + groups.get(i).dpf;
					group.groupCalc();
				}
				this.streamp = group.streamp.copy();
				this.streamc = group.streamc.copy();
			}
			double temp = 0;
			double tempQp = 0;
			for (MGroup group : groups) {
				tempQp += group.streamp.q;
				temp += group.streamp.tds * group.streamp.q;
			}
			this.streamp.q = tempQp;
			this.streamp.tds = temp / systemPQ;
			this.systemRs = 1 - this.streamp.tds / this.streamf.tds;
			this.systemFp = this.streamp.q * 1000 / (moN() * this.moarea);
			this.systemHSL = this.streamp.q / this.streamf.q;
			if (this.streamp.q / systemPQ > 0.995 && this.streamp.q / systemPQ < 1.005) {
				break;
			} else if (this.streamp.q < systemPQ) {
				this.streams.pf = 1.01 * this.streams.pf;
			} else if (this.streamp.q > systemPQ) {
				this.streams.pf = 0.99 * this.streams.pf;
			}
			if (new Date().getTime() - start > 5000) {
				throw new TimeoutException("系统模型计算超时");
			}
		}
	}

	public String warning() {
		StringBuilder warn = new StringBuilder();
		for (int t = 0; t < groups.size(); t++) {
			for (int m = 0; m < groups.get(t).ni; m++) {
				MBranch branch = groups.get(t).branchs.get(m);
				if (branch.streamp.q > 1.7 || branch.moY() > 0.3 || branch.streamc.q < 3 || branch.streamf.pf > 4.1
						|| branch.streamf.q > 17) {
					warn.append(String.format("第%d段第%d支膜设计超出限值", (t + 1), (m + 1)));
					if (branch.streamp.q > 1.7) {
						warn.append(", 产水量 >1.7m³/h");
					}
					if (branch.moY() > 0.3) {
						warn.append(", 回收率>30%");
					}
					if (branch.streamc.q < 3) {
						warn.append(", 浓水量<3m³/h");
					}
					if (branch.streamf.pf > 4.1) {
						warn.append(", 最大压力>4.1MPa");
					}
					if (branch.streamf.q > 17) {
						warn.append(", 最大进水量>17m³/h");
					}
					warn.append("\n");
				}
			}
		}
		if (warn.length() == 0) {
			warn.append("无");
		}

		return warn.toString();
	}
}
