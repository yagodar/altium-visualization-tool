package ru.ifmo.avt.vca;

import static java.lang.Math.*;

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

	    if (pcbModel.getWidth() > pcbModel.getHeight()) {
		max = pcbModel.getWidth();
		min = pcbModel.getHeight();
	    } else {
		min = pcbModel.getWidth();
		max = pcbModel.getHeight();
	    }

	    min *= MULT_MIL_TO_M;
	    max *= MULT_MIL_TO_M;

	    double Bv = 0.0;
	    
	    double F0 = getF0(pcbModel, fixing);
	    
	    pcbModel.setFrequency(frequency);
	    pcbModel.setOwnFrequency(frequency);

	    double S0 = getS0(pcbModel, frequency, acceleration);
	    
	    pcbModel.setDisplacement(S0);
	    
	    double Bd = 0.003 * max;
	    
	    pcbModel.setMaxDeflection(Bd);

	    for (IPcbElementModelForVca e : pcbModel.getElementsForVca()) {
		double Ex = (e.getSrcLocation().getX() + e.getWidth() / 2) / pcbModel.getWidth();
		double Ey = (e.getSrcLocation().getY() + e.getHeight() / 2) / pcbModel.getHeight();
		double Yxy = getY(Ex, Ey, F0, frequency, fixing);
		double Av = acceleration * Yxy;
		e.setVibroAcceleration(Av);
		double Sv = S0 * Yxy;
		if(Bv < abs(Sv - S0))
		    Bv = abs(Sv - S0);
	    }
	    
	    pcbModel.setDeflection(Bv);
	} else {
	    System.out.println(PcbVibroConditionsAnalyzer.class.getSimpleName() + " Error! pcbModel is null!");
	}
    }

    private double getY(double ex, double ey, double F0, double frequency, int fixing) {
	double powE = pow(getE(F0), 2);
	double powN = pow(getN(frequency, F0), 2);
	double kx = getKoefForm(ex, fixing);
	double ky = getKoefForm(ey, fixing);
	return sqrt(pow(1 + (kx * ky - 1) * powN, 2) + powE * powN) / sqrt(pow(1 - powN, 2) + powE * powN);
    }

    private double getKoefForm(double exy, int fixing) {
	switch ((int)koef[fixing][4]) {
	case 0:
		return 1;
	case 1:
		return 1.34 * sin(PI * exy);
	case 2:
		return 0.67 *  (1 - cos(2 * PI * exy));
	case 3:
		return 1.75 * sin(PI * exy) * cos(0.5 * PI * exy);
	case 4:
		return 1.6 * pow(exy, 2);
	case 5:
		return -3.125 * pow(exy - 0.4, 2) + 0.5;
	default:
	    return 1;
	}
    }

    private double getN(double frequency, double F0) {
	return frequency / F0;
    }

    private double getE(double F0) {
	return 1 / sqrt(F0);
    }

    private double getS0(IPcbModelForVca pcbModel, double frequency, double acceleration) {
	return acceleration / (4 * pow(Math.PI, 2) * pow(frequency, 2));
    }

    private double getF0(IPcbModelForVca pcbModel, int fixing) {
	return (getKoefAlfa(fixing) * sqrt(getD(pcbModel) / getM(pcbModel)) * getKm(pcbModel) * getKv(pcbModel)) / (2 * PI * pow(max, 2));
    }

    private double getKv(IPcbModelForVca pcbModel) {
	return 1 / sqrt(1 + pcbModel.getWeightAllElements() / getMpp(pcbModel));
    }

    private double getKm(IPcbModelForVca pcbModel) {
	return 0;
    }

    private double getM(IPcbModelForVca pcbModel) {
	return getMpp(pcbModel) / (max * min);
    }

    private double getMpp(IPcbModelForVca pcbModel) {
	return pcbModel.getDensity() * max * min * pcbModel.getDepth();
    }

    private double getD(IPcbModelForVca pcbModel) {
	return (pcbModel.getModuleElasticity() * pow(pcbModel.getDepth(), 3)) / (12 * (1 - pcbModel.getPuassonsCoefficient()));
    }

    private double getKoefAlfa(int fixing) {
	return koef[fixing][0] * sqrt(koef[fixing][1] + koef[fixing][2] * pow(max / min, 2) + koef[fixing][3] * pow(max / min, 4));
    }

    private static double max;
    private static double min;

    private static PcbVibroConditionsAnalyzer INSTANCE;

    private static final double MULT_MIL_TO_M = 0.0000254;

    private static final double[][] koef = {
	    { 9.87, 1, 2.33, 2.44, 1 },
	    { 15.42, 1, 0.95, 0.41, 3 },
	    { 9.87, 1, 2.57, 5.14, 1 },
	    { 22.37, 1, 0.48, 0.19, 2 },
	    { 15.42, 1, 1.11, 1, 3 },
	    { 22.37, 1, 0.57, 0.47, 2 },
	    { 15.42, 1, 1.19, 2.1, 3 },
	    { 22.37, 1, 0.61, 1, 2 },
	    { 9.87, 0, 0.43, 1, 5 },
	    { 9.87, 1, 0.43, 0, 1 },
	    { 3.52, 1, 5.97, 40.5, 4 },
	    { 22.37, 1, 0.14, 0.02, 2 },
	    { 3.52, 1, 5.56, 19.2, 3 },
	    { 15.42, 1, 0.29, 0.05, 4 },
	    { 22.37, 1, 0.1, 0, 2 },
	    { 15.42, 1, 0.34, 0, 3 },
	    { 9.87, 1, 2, 1, 1 },
	    { 3.52, 0, 1, 0, 5 },
	    { 9.87, 0, 0, 1, 0 },
	    { 9.87, 1, 0, 0, 1 },
	    { 22.37, 1, 0, 0, 2 },
	    { 22.37, 0, 0, 1, 0 },
	    { 3.52, 1, 0, 0, 4 },
	    { 3.52, 0, 0, 1, 0 },
	    { 15.42, 1, 0, 0, 3 },
	    { 15.42, 0, 0, 1, 0 },
	    { 3.52, 1, 2.48, 1, 4 },
	    { 3.52, 1, 1.58, 0, 4 },
	    { 3.52, 0, 1.58, 1, 5 },
	    { 22.37, 0, 0, 1, 0 },
	    { 15.42, 0, 0.34, 1, 5 } };
}
