package ru.ifmo.avt.tca;

import java.util.ArrayList;


public class PcbThermalConditionsAnalyzer {
	public static PcbThermalConditionsAnalyzer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PcbThermalConditionsAnalyzer();
		}
		
		return INSTANCE;
	}
	
	public void analyzePcbModel(IPcbModelForTca pcbModel) {
		if(pcbModel != null) {
			ArrayList<IPcbElementModelForTca> poweredElements = selectPoweredPcbElementModels(pcbModel);
			if(poweredElements.size() != 0) {
				double[][] generalMatrixA = createGeneralMatrix(pcbModel, poweredElements);
			}
			else {
				System.out.println(PcbThermalConditionsAnalyzer.class.getSimpleName() + " Warning! There is no powered elements in pcbModel!");
			}
		}
		else {
			System.out.println(PcbThermalConditionsAnalyzer.class.getSimpleName() + " Error! pcbModel is null!");
		}
	}
	
	private PcbThermalConditionsAnalyzer() {};
	
	private ArrayList<IPcbElementModelForTca> selectPoweredPcbElementModels(IPcbModelForTca pcbModel) {
		ArrayList<IPcbElementModelForTca> poweredElements = new ArrayList<IPcbElementModelForTca>();
		
		for (IPcbElementModelForTca element : pcbModel.getElementsForTca()) {
			if(element.getPower() > 0.0) {
				poweredElements.add(element);
			}
		}
		
		poweredElements.trimToSize();
		
		return poweredElements;
	}
	
	private double[][] createGeneralMatrix(IPcbModelForTca pcbModel, ArrayList<IPcbElementModelForTca> poweredElements) {
		double generalMatrix[][] = new double[poweredElements.size()][poweredElements.size()];
		
		double envThermalConduct = pcbModel.getEnvThermalConduct();
		double boardThermalConduct = pcbModel.getThermalConduct();
		
		double thermalConductsBodyBody[][] = calcThermalConductsBodyBody(envThermalConduct, poweredElements);
		double thermalConductsBodyEnv[][] = calcThermalConductsBodyEnv(envThermalConduct, boardThermalConduct, poweredElements);
		
		for (int rowIndx = 0; rowIndx < generalMatrix.length; rowIndx++) {
			for (int columnIndx = 0; columnIndx < generalMatrix[rowIndx].length; columnIndx++) {
				if(rowIndx == columnIndx) {
					generalMatrix[rowIndx][columnIndx] = 0.0;
					
					for (int j = 0; j < thermalConductsBodyBody[rowIndx].length; j++) {
						generalMatrix[rowIndx][columnIndx] += thermalConductsBodyBody[rowIndx][j];
					}
					
					for (int k = 0; k < thermalConductsBodyEnv[rowIndx].length; k++) {
						generalMatrix[rowIndx][columnIndx] += thermalConductsBodyBody[rowIndx][k];
					}
				}
				else {
					generalMatrix[rowIndx][columnIndx] = -thermalConductsBodyBody[rowIndx][columnIndx];
				}
			}
		}
		
		return generalMatrix;
	}
	
	private double[][] calcThermalConductsBodyBody(double envThermalConduct, ArrayList<IPcbElementModelForTca> poweredElements) {
		double result[][] = new double[poweredElements.size()][poweredElements.size()];
		
		double sectionalArea = 0.0;
		double lengthOfArea = 0.0;
		boolean triangleMatrixBegan;
		
		for (int i = 0; i < poweredElements.size(); i++) {
			triangleMatrixBegan = false;
			for (int j = 0; j < poweredElements.size(); j++) {
				if(i == j) {
					result[i][j] = 0.0;
					triangleMatrixBegan = true;
				}
				else if(triangleMatrixBegan) {
					sectionalArea = ((Math.sqrt(Math.pow(poweredElements.get(i).getHeight(), 2.0) + Math.pow(poweredElements.get(i).getWidth(), 2)) + Math.sqrt(Math.pow(poweredElements.get(j).getHeight(), 2) + Math.pow(poweredElements.get(j).getWidth(), 2.0))) / 2.0) * (poweredElements.get(i).getDepth() + poweredElements.get(j).getDepth() / 2.0);
					lengthOfArea = Math.sqrt(Math.pow(poweredElements.get(j).getLocation().getX() - poweredElements.get(i).getLocation().getX(), 2) + Math.pow(poweredElements.get(j).getLocation().getY() - poweredElements.get(i).getLocation().getY(), 2));
					result[i][j] = (envThermalConduct * sectionalArea) / lengthOfArea;
				}
			}
		}
		
		return result;
	}
	
	private double[][] calcThermalConductsBodyEnv(double envThermalConduct, double boardThermalConduct, ArrayList<IPcbElementModelForTca> poweredElements) {
		double result[][] = null;
		
		return result;
	}
	
	private static PcbThermalConditionsAnalyzer INSTANCE;
}
