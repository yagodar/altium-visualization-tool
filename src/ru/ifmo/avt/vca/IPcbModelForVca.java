package ru.ifmo.avt.vca;

import java.util.List;

public interface IPcbModelForVca extends IPcbObjectModelForVca {
	public List<IPcbElementModelForVca> getElementsForVca();
	
	public double getModuleElasticity();//модуль упругости Н/м^2
	
	public double getDensity();//плотность кг/м^3
	
	public double getPuassonsCoefficient();//коэффициент Пуассона
	
	public double getWeightAllElements();//масса всех элементов кг
}
