package ru.ifmo.avt.vca;

import java.util.ArrayList;


public class PcbVibroConditionsAnalyzer {
	public static PcbVibroConditionsAnalyzer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PcbVibroConditionsAnalyzer();
		}
		
		return INSTANCE;
	}
	
	public void analyzePcbModel(IPcbModelForVca pcbModel) {
		if(pcbModel != null) {
			ArrayList<IPcbElementModelForVca> poweredElements = selectPoweredPcbElementModels(pcbModel);
			if(poweredElements.size() != 0) {
				double[][] thermalConductsBodyEnv = calcThermalConductsBodyEnv(pcbModel, poweredElements);
				
				double[][] generalMatrixA = createGeneralMatrixA(pcbModel, poweredElements, thermalConductsBodyEnv);
				double[] freeTermsMatrixB = createFreeTermsMatrixB(pcbModel, poweredElements, thermalConductsBodyEnv);
				
				double[] elementsTemperature = calcUnknownMatrixX(generalMatrixA, freeTermsMatrixB);
				
				for (int i = 0; i < elementsTemperature.length; i++) {
					poweredElements.get(i).setTemperature(elementsTemperature[i]);
				}
			}
			else {
				System.out.println(PcbVibroConditionsAnalyzer.class.getSimpleName() + " Warning! There is no powered elements in pcbModel!");
			}
		}
		else {
			System.out.println(PcbVibroConditionsAnalyzer.class.getSimpleName() + " Error! pcbModel is null!");
		}
	}
	
	private PcbVibroConditionsAnalyzer() {};
	
	private ArrayList<IPcbElementModelForVca> selectPoweredPcbElementModels(IPcbModelForVca pcbModel) {
		ArrayList<IPcbElementModelForVca> poweredElements = new ArrayList<IPcbElementModelForVca>();
		
		for (IPcbElementModelForVca element : pcbModel.getElementsForTca()) {
			if(element.getPower() > 0.0 && element.getWidth() > 0.0 && element.getHeight() > 0.0 && element.getDepth() > 0.0) {
				poweredElements.add(element);
			}
		}
		
		poweredElements.trimToSize();
		
		return poweredElements;
	}
	
	private double[][] createGeneralMatrixA(IPcbModelForVca pcbModel, ArrayList<IPcbElementModelForVca> poweredElements, double[][] thermalConductsSumBodyEnv) {
		double result[][] = new double[poweredElements.size()][poweredElements.size()];
		
		double envThermalConduct = pcbModel.getEnvThermalConduct();
		
		double thermalConductsBodyBody[][] = calcThermalConductsBodyBody(envThermalConduct, poweredElements);
		
		for (int rowIndx = 0; rowIndx < result.length; rowIndx++) {
			for (int columnIndx = 0; columnIndx < result[rowIndx].length; columnIndx++) {
				if(rowIndx == columnIndx) {
					for (int j = 0; j < thermalConductsBodyBody[rowIndx].length; j++) {
						result[rowIndx][columnIndx] += thermalConductsBodyBody[rowIndx][j];
					}
					
					for (int k = 0; k < thermalConductsSumBodyEnv[rowIndx].length; k++) {
						result[rowIndx][columnIndx] += thermalConductsSumBodyEnv[rowIndx][k];
					}
				}
				else {
					result[rowIndx][columnIndx] = -thermalConductsBodyBody[rowIndx][columnIndx];
				}
			}
		}
		
		return result;
	}
	
	private double[][] calcThermalConductsBodyBody(double envThermalConduct, ArrayList<IPcbElementModelForVca> poweredElements) {
		double result[][] = new double[poweredElements.size()][poweredElements.size()];
		
		double sectionalArea = 0.0;
		double lengthOfArea = 0.0;
		boolean triangleMatrixBegan;
		
		for (int i = 0; i < poweredElements.size(); i++) {
			triangleMatrixBegan = false;
			for (int j = 0; j < poweredElements.size(); j++) {
				if(i == j) {
					triangleMatrixBegan = true;
				}
				else if(triangleMatrixBegan) {
					sectionalArea = ((Math.sqrt(Math.pow(poweredElements.get(i).getHeight(), 2.0) + Math.pow(poweredElements.get(i).getWidth(), 2.0)) + Math.sqrt(Math.pow(poweredElements.get(j).getHeight(), 2.0) + Math.pow(poweredElements.get(j).getWidth(), 2.0))) / 2.0) * (poweredElements.get(i).getDepth() + poweredElements.get(j).getDepth() / 2.0);
					lengthOfArea = Math.sqrt(Math.pow(poweredElements.get(j).getSrcLocation().getX() - poweredElements.get(i).getSrcLocation().getX(), 2.0) + Math.pow(poweredElements.get(j).getSrcLocation().getY() - poweredElements.get(i).getSrcLocation().getY(), 2.0));
					result[i][j] = (envThermalConduct * sectionalArea) / lengthOfArea;
					result[j][i] = result[i][j];
				}
			}
		}
		
		return result;
	}
	
	private double[][] calcThermalConductsBodyEnv(IPcbModelForVca pcbModel, ArrayList<IPcbElementModelForVca> poweredElements) {
		double[][] result = new double[poweredElements.size()][6];

		double sectionalArea = 0.0;
		double lengthOfArea = 0.0;

		double envThermalConduct = pcbModel.getEnvThermalConduct();
		double boardThermalConduct = pcbModel.getThermalConduct();

		double boardWidth = pcbModel.getWidth();
		double boardHeight = pcbModel.getHeight();
		double boardDepth = pcbModel.getDepth();

		double maxElementsDepth = getMaxDepth(poweredElements);

		double elementWidth = 0.0;
		double elementHeight = 0.0;
		double elementDepth = 0.0;

		double elementLocX = 0.0;
		double elementLocY = 0.0;

		for(int i = 0; i < poweredElements.size(); i++) {
			elementWidth = poweredElements.get(i).getWidth();
			elementHeight = poweredElements.get(i).getHeight();
			elementDepth = poweredElements.get(i).getDepth();

			if(poweredElements.get(i).getSrcLocation() != null) {
				elementLocX = poweredElements.get(i).getSrcLocation().getX();
				elementLocY = poweredElements.get(i).getSrcLocation().getY();
			}

			sectionalArea = elementWidth * elementHeight;

			//bottom
			if(boardDepth > 0.0) {
				result[i][0] = (boardThermalConduct * sectionalArea) / boardDepth;
			}

			//top
			lengthOfArea = maxElementsDepth - elementDepth;
			if(lengthOfArea > 0.0) {
				result[i][1] = (envThermalConduct * sectionalArea) / lengthOfArea;
			}

			sectionalArea = elementHeight * elementDepth;

			//left
			if(elementLocX > 0.0) {
				result[i][2] = (envThermalConduct * sectionalArea) / elementLocX;
			}

			//right
			lengthOfArea = boardWidth - elementLocX - elementWidth;
			if(lengthOfArea > 0.0) {
				result[i][3] = (envThermalConduct * sectionalArea) / lengthOfArea;
			}

			sectionalArea = elementWidth * elementDepth;

			//forward
			if(elementLocY > 0.0) {
				result[i][4] = (envThermalConduct * sectionalArea) / elementLocY;
			}

			//back
			lengthOfArea = boardHeight - elementLocY - elementHeight;
			if(lengthOfArea > 0.0) {
				result[i][5] = (envThermalConduct * sectionalArea) / lengthOfArea;
			}
		}

		return result;
	}
	
	private double getMaxDepth(ArrayList<IPcbElementModelForVca> poweredElements) {
		double result = 0.0;
		double depth = 0.0;
		
		result = poweredElements.get(0).getDepth();
		
		for (int i = 1; i < poweredElements.size(); i++) {
			depth = poweredElements.get(i).getDepth();
			
			if(result < depth) {
				result = depth;
			}
		}
		
		return result;
	}
	
	private double[] createFreeTermsMatrixB(IPcbModelForVca pcbModel, ArrayList<IPcbElementModelForVca> poweredElements, double[][] thermalConductsSumBodyEnv) {
		double[] result = new double[poweredElements.size()];
		
		double envTemperature = pcbModel.getEnvTemperature();
		
		for (int i = 0; i < poweredElements.size(); i++) {
			result[i] = poweredElements.get(i).getPower() / MULT_MIL_TO_M; //Так как расстояния в милах, а мощность в Вт = Н*м/с
			
			for (int j = 0; j < thermalConductsSumBodyEnv[i].length; j++) {
				result[i] += thermalConductsSumBodyEnv[i][j] * envTemperature;
			}
		}
		
		return result;
	}
	
	private double[] calcUnknownMatrixX(double[][] generalMatrixA, double[] freeTermsMatrixB) {
		double topMatrixEl;
		double multMatrixEl;
		
		for (int rowIndx = 0; rowIndx < generalMatrixA.length; rowIndx++) {
			topMatrixEl = generalMatrixA[rowIndx][rowIndx];
			
			for (int columnIndx = rowIndx; columnIndx < generalMatrixA[rowIndx].length; columnIndx++) {
				generalMatrixA[rowIndx][columnIndx] = generalMatrixA[rowIndx][columnIndx] / topMatrixEl;
			}
			
			freeTermsMatrixB[rowIndx] = freeTermsMatrixB[rowIndx] / topMatrixEl;
			
			for (int multRowIndx = rowIndx + 1; multRowIndx < generalMatrixA.length; multRowIndx++) {
				multMatrixEl = -generalMatrixA[multRowIndx][rowIndx];
				
				for (int multColumnIndx = rowIndx; multColumnIndx < generalMatrixA[multRowIndx].length; multColumnIndx++) {
					generalMatrixA[multRowIndx][multColumnIndx] += generalMatrixA[rowIndx][multColumnIndx] * multMatrixEl;
				}
				
				freeTermsMatrixB[multRowIndx] += freeTermsMatrixB[rowIndx] * multMatrixEl;
			}
		}
		
		
		for (int multColumnIndx = generalMatrixA[0].length - 1; multColumnIndx > 0; multColumnIndx--) {
			for (int multRowIndx = multColumnIndx - 1; multRowIndx >= 0; multRowIndx--) {
				multMatrixEl = -generalMatrixA[multRowIndx][multColumnIndx];
				
				generalMatrixA[multRowIndx][multColumnIndx] += generalMatrixA[multColumnIndx][multColumnIndx] * multMatrixEl;
				
				freeTermsMatrixB[multRowIndx] += freeTermsMatrixB[multColumnIndx] * multMatrixEl;
			}
		}
		
		return freeTermsMatrixB;
	}
	
	private static PcbVibroConditionsAnalyzer INSTANCE;
	
	private static final double MULT_MIL_TO_M = 0.0000254;
}
