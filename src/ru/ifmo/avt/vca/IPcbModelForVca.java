package ru.ifmo.avt.vca;

import java.util.List;

public interface IPcbModelForVca extends IPcbObjectModelForVca {
	public double getEnvThermalConduct();
	
	public double getEnvTemperature();
	
	public List<IPcbElementModelForVca> getElementsForTca();
}
