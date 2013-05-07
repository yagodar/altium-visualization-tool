package ru.ifmo.avt.tca;


public class ThermalConditionsAnalyzer {
	public static ThermalConditionsAnalyzer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ThermalConditionsAnalyzer();
		}
		
		return INSTANCE;
	}
	
	public void analyzePcbModel(IPcbModelForTca pcbModel) {
		//TODO
	}
	
	private ThermalConditionsAnalyzer() {};
	
	private static ThermalConditionsAnalyzer INSTANCE;
}
