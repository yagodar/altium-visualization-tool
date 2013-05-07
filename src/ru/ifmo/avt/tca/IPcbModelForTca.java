package ru.ifmo.avt.tca;

import java.util.List;

public interface IPcbModelForTca extends IPcbObjectModelForTca {
	public double getEnvThermalConduct();
	
	public double getEnvTemperature();
	
	public List<IPcbElementModelForTca> getElementsForTca();
}
