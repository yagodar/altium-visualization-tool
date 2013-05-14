package ru.ifmo.avt.vca;


public interface IPcbElementModelForVca extends IPcbObjectModelForVca {
	public double getPermissibleLoad(); //допустимая нагрузка [g] по умолчанию 20 
}
