package ru.ifmo.avt.vca;


public interface IPcbElementModelForVca extends IPcbObjectModelForVca {
	public double getPermissibleLoad(); //допустимая нагрузка [g] по умолчанию 20 

	public void setVibroAcceleration(double acceleration);// виброускорение [м/с^2] по умолчанию 0
}
