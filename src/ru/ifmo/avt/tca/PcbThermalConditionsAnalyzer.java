package ru.ifmo.avt.tca;


public class PcbThermalConditionsAnalyzer {
	public static PcbThermalConditionsAnalyzer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PcbThermalConditionsAnalyzer();
		}
		
		return INSTANCE;
	}
	
	public void analyzePcbModel(IPcbModelForTca pcbModel) {
		//TODO
	}
	
	private PcbThermalConditionsAnalyzer() {};
	
	private static PcbThermalConditionsAnalyzer INSTANCE;
}
