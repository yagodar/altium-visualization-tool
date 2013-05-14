package ru.ifmo.avt.vca;

public class PcbVibroConditionsAnalyzer {

	private PcbVibroConditionsAnalyzer() {
	};

	public static PcbVibroConditionsAnalyzer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PcbVibroConditionsAnalyzer();
		}

		return INSTANCE;
	}

	public void analyzePcbModel(IPcbModelForVca pcbModel, double frequency, double acceleration, int fixing) {
		if (pcbModel != null) {
			double koefAlfa = getKoefAlfa(fixing);
		} else {
			System.out.println(PcbVibroConditionsAnalyzer.class.getSimpleName()
					+ " Error! pcbModel is null!");
		}
	}

	private double getKoefAlfa(int fixing) {
		
		return 0;
	}

	private static PcbVibroConditionsAnalyzer INSTANCE;

	private static final double MULT_MIL_TO_M = 0.0000254;
}
