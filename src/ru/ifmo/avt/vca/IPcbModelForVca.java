package ru.ifmo.avt.vca;

import java.util.List;

public interface IPcbModelForVca extends IPcbObjectModelForVca {
	public List<IPcbElementModelForVca> getElementsForVca();
	
	public double getModuleElasticity();//модуль упругости Н/м^2 по умолчанию 3,02 * 10^10 
	
	public double getDensity();//плотность кг/м^3 по умолчанию 2,05 * 10^3
	
	public double getPuassonsCoefficient();//коэффициент Пуассона по умолчанию 0,22
	
	public double getWeightAllElements();//масса всех элементов кг по умолчанию 0

	public void setFrequency(double frequency);// частота [герцы] по умолчанию 0;

	public void setDisplacement(double displacement);// вибросмещение [м] по умочанию 0;

	public void setOwnFrequency(double frequency);// Собственная частота [герцы] по умолчанию 0;

	public void setMaxDeflection(double maxDeflection);// допустимый прогиб [м] по умолчанию 0

	public void setDeflection(double deflection);// максимальный прогиб [м] по умолчанию 0
}
