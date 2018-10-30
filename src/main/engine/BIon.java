package engine;

public class BIon {
	// 摩尔质量 g/mol
	public double Mj;
	// 当量浓度 meq/L
	public int Zj;

	public BIon(Ion ion) {
		switch (ion) {
		case Na:
			Mj = 22.99;
			Zj = 1;
			break;
		case Cl:
			Mj = 35.45;
			Zj = 1;
			break;
		}
	}
}
