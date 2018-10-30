package engine;

public class Water {
	// 压力 MPa
	public double pf;
	// 流量
	public double q;
	// 温度
	public double temperature = 25;

	// PH
	private double pH = 7.6;

	public double pH() {
		return this.pH;
	}

	public void pH(double val) throws IllegalArgumentException {
		if (val < 0 || val > 14) {
			throw new IllegalArgumentException("输入pH不在有效范围内[0~14]。");
		}
		this.pH = val;
	}

	// tds mg/L
	public double tds = 2000;
	// ff 污染因子
	public double ff = 0.85;

	// 渗透压 MPa
	private double paif = 0;

	public double paif() {
		double sum = 0;
		for (Ion ion : Ion.values()) {
			sum += new BIon(ion).Mj;
		}
		paif = 8.31 * Math.pow(10, -6) * (273 + temperature) * (2 * tds / sum);
		return paif;
	}

	public void paif(double val) {
		this.paif = val;
	}

	// 温度校正系数
	public double tcf() {
		double temp;
		if (temperature > 25) {
			temp = Math.exp(2640 * (1.0 / 298 - 1.0 / (273 + temperature)));
		} else {
			temp = Math.exp(3020 * (1.0 / 298 - 1.0 / (273 + temperature)));
		}
		return temp;
	}

	public Water copy() {
		Water water = new Water();
		water.temperature = this.temperature;
		water.pH = this.pH;
		water.ff = this.ff;
		water.tds = this.tds;
		water.pf = this.pf;
		water.paif = this.paif;
		water.q = this.q;
		return water;
	}
}
