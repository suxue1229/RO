package engine;

import java.util.concurrent.TimeoutException;

public class MBranch {
	// 进水
	public Water streamf;
	// 产水
	public Water streamp;
	// 浓水
	public Water streamc;
	// 浓水流通阻力系数 k
	public double k;
	// 透水系数初算值 A
	public double A;
	// 膜面积
	public double moarea;
	// 系统膜元件平均压力降
	public double moavgPfc;
	// 系统平均回收率
	public double moavgY;
	private final double a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13;
	private final double b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13;

	public MBranch(String motype) throws IllegalArgumentException {
		switch (motype) {
		case "BW_8040":
			this.k = 0.01;
			this.A = 0.125;
			this.moarea = 37;
			a0 = 7.016 * Math.pow(10, -2);
			a1 = 1.850 * Math.pow(10, -3);
			a2 = 3.188 * Math.pow(10, -6);
			a3 = 4.812 * Math.pow(10, -4);
			a4 = -4.398 * Math.pow(10, -2);
			a5 = -2.575 * Math.pow(10, -9);
			a6 = 4.668 * Math.pow(10, -5);
			a7 = -3.874 * Math.pow(10, -5);
			a8 = -3.282 * Math.pow(10, -6);
			a9 = -3.750 * Math.pow(10, -3);
			a10 = 6.780 * Math.pow(10, -5);
			a11 = -4.114 * Math.pow(10, -12);
			a12 = 1.282 * Math.pow(10, -4);
			a13 = 3.600 * Math.pow(10, -2);

			b0 = 7.176 * Math.pow(10, -2);
			b1 = -2.920 * Math.pow(10, -3);
			b2 = 1.862 * Math.pow(10, -6);
			b3 = 3.890 * Math.pow(10, -3);
			b4 = -8.096 * Math.pow(10, -2);
			b5 = -1.655 * Math.pow(10, -7);
			b6 = -2.073 * Math.pow(10, -4);
			b7 = 4.340 * Math.pow(10, -3);
			b8 = -3.746 * Math.pow(10, -6);
			b9 = -1.844 * Math.pow(10, -2);
			b10 = 3.200 * Math.pow(10, -4);
			b11 = 2.537 * Math.pow(10, -10);
			b12 = 8.878 * Math.pow(10, -4);
			b13 = 1.043 * Math.pow(10, -1);
			break;
		case "ULP_8040":
			this.k = 0.0079;
			this.A = 0.235;
			this.moarea = 37;
			a0 = 1.144 * Math.pow(10, -1);
			a1 = 4.480 * Math.pow(10, -3);
			a2 = 7.346 * Math.pow(10, -6);
			a3 = -9.245 * Math.pow(10, -5);
			a4 = -6.363 * Math.pow(10, -2);
			a5 = -1.705 * Math.pow(10, -7);
			a6 = 5.958 * Math.pow(10, -5);
			a7 = -5.743 * Math.pow(10, -4);
			a8 = -6.242 * Math.pow(10, -6);
			a9 = -3.860 * Math.pow(10, -3);
			a10 = 9.771 * Math.pow(10, -5);
			a11 = 6.744 * Math.pow(10, -11);
			a12 = 1.431 * Math.pow(10, -4);
			a13 = 4.683 * Math.pow(10, -2);

			b0 = 1.769 * Math.pow(10, -1);
			b1 = -1.047 * Math.pow(10, -2);
			b2 = 7.361 * Math.pow(10, -6);
			b3 = 1.453 * Math.pow(10, -2);
			b4 = -2.798 * Math.pow(10, -1);
			b5 = -8.449 * Math.pow(10, -7);
			b6 = -8.260 * Math.pow(10, -4);
			b7 = 1.531 * Math.pow(10, -2);
			b8 = -1.596 * Math.pow(10, -5);
			b9 = -6.571 * Math.pow(10, -2);
			b10 = 8.367 * Math.pow(10, -4);
			b11 = 1.529 * Math.pow(10, -9);
			b12 = 3.190 * Math.pow(10, -3);
			b13 = 3.716 * Math.pow(10, -1);
			break;
		case "SW_8040":
			this.k = 0.0075;
			this.A = 0.055;
			this.moarea = 37;
			a0 = 5.307 * Math.pow(10, -2);
			a1 = 1.520 * Math.pow(10, -3);
			a2 = 2.599 * Math.pow(10, -7);
			a3 = 3.199 * Math.pow(10, -4);
			a4 = -9.960 * Math.pow(10, -3);
			a5 = -1.124 * Math.pow(10, -8);
			a6 = 1.086 * Math.pow(10, -5);
			a7 = -7.040 * Math.pow(10, -5);
			a8 = -6.090 * Math.pow(10, -8);
			a9 = 3.041 * Math.pow(10, -5);
			a10 = 1.395 * Math.pow(10, -5);
			a11 = 1.602 * Math.pow(10, -12);
			a12 = -2.526 * Math.pow(10, -5);
			a13 = 8.628 * Math.pow(10, -4);

			b0 = 3.755 * Math.pow(10, -2);
			b1 = -9.262 * Math.pow(10, -4);
			b2 = 1.683 * Math.pow(10, -7);
			b3 = 8.081 * Math.pow(10, -4);
			b4 = -6.770 * Math.pow(10, -3);
			b5 = -2.559 * Math.pow(10, -8);
			b6 = -7.182 * Math.pow(10, -5);
			b7 = 4.449 * Math.pow(10, -4);
			b8 = -8.373 * Math.pow(10, -8);
			b9 = -2.248 * Math.pow(10, -4);
			b10 = 9.939 * Math.pow(10, -5);
			b11 = 5.506 * Math.pow(10, -12);
			b12 = 4.760 * Math.pow(10, -5);
			b13 = 7.754 * Math.pow(10, -4);
			break;
		default:
			throw new IllegalArgumentException("膜型号不支持！");
		}
	}

	// 透水系数
	private double moA() {
		double moA = a0 + a1 * streamf.temperature + a2 * streamf.tds + a3 * streamf.q + a4 * streamf.pf
				+ a5 * streamf.temperature * streamf.tds + a6 * streamf.temperature * streamf.q
				+ a7 * streamf.temperature * streamf.pf + a8 * streamf.tds * streamf.pf + a9 * streamf.q * streamf.pf
				+ a10 * streamf.temperature * streamf.temperature + a11 * streamf.tds * streamf.tds
				+ a12 * streamf.q * streamf.q + a13 * streamf.pf * streamf.pf;
		return moA;
	}

	// 透盐系数
	private double moB() {
		double moB = b0 + b1 * streamf.temperature + b2 * streamf.tds + b3 * streamf.q + b4 * streamf.pf
				+ b5 * streamf.temperature * streamf.tds + b6 * streamf.temperature * streamf.q
				+ b7 * streamf.temperature * streamf.pf + b8 * streamf.tds * streamf.pf + b9 * streamf.q * streamf.pf
				+ b10 * streamf.temperature * streamf.temperature + b11 * streamf.tds * streamf.tds
				+ b12 * streamf.q * streamf.q + b13 * streamf.pf * streamf.pf;
		return moB;
	}

	// 膜压力降 MPa
	public double moDropPf() {
		double a = (this.streamf.q + this.streamc.q) * 4.403 / 2;
		return (k / 145.038) * Math.pow(a, 1.7);
	}

	// 膜回收率
	public double moY() {
		return this.streamp.q / this.streamf.q;
	}

	// 脱盐率
	public double moR() {
		return 1 - (this.streamp.tds / this.streamf.tds);
	}

	// 平均渗透压
	private double mopai(double m) {
		return this.streamf.paif() * Math.exp(0.7 * m) * (1 + Math.pow((1 - m), -1)) / 2;
	}

	// 产水量
	private double moQp(double m, double n) {
		return (1.0 / 4.061) * this.moarea * moA() * streamf.tcf() * streamf.ff * (this.streamf.pf - m / 2 - mopai(n));
	}

	// 浓水量
	private double moQc() {
		return this.streamf.q - this.streamp.q;
	}

	public void branchCalc() throws Exception {
		this.streamp = this.streamf.copy();
		this.streamc = this.streamf.copy();
		int count = 0;
		double temp1, temp2;
		// if (moQp(this.moavgPfc, this.moavgY) > 0) {
		this.streamp.q = moQp(this.moavgPfc, this.moavgY);
		this.streamc.q = moQc();
		temp1 = moDropPf();
		temp2 = moY();
		while (!compare(temp1, this.moavgPfc, temp2, this.moavgY)) {
			this.moavgPfc = temp1;
			this.moavgY = temp2;
			// if (moQp(temp1, temp2) > 0) {
			this.streamp.q = moQp(temp1, temp2);
			this.streamc.q = moQc();
			temp1 = moDropPf();
			temp2 = moY();
			// } else {
			// break;
			// }
			count++;
			if (count > 10000) {
				throw new TimeoutException("压力降与回收率计算超时");
			}
		}
		// }
		this.streamp.tds = this.streamf.tds / (1 + 1000 * this.streamp.q / (moB() * this.moarea * this.streamf.tcf()));
		this.streamc.tds = (this.streamf.q * this.streamf.tds - this.streamp.q * this.streamp.tds) / this.streamc.q;
	}

	public boolean compare(double datapfc, double opfc2, double dataY, double y2) {
		if (((datapfc / opfc2) > 0.995 && (datapfc / opfc2) < 1.005)
				|| ((dataY / y2) > 0.995 && (dataY / y2) < 1.005)) {
			return true;
		} else {
			return false;
		}
	}
}
