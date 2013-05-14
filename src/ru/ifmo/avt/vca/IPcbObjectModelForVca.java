package ru.ifmo.avt.vca;

import java.awt.Point;

public interface IPcbObjectModelForVca {
	public double getThermalConduct();
	
	public double getTemperature();
	
	public void setTemperature(double temperature);
	
	public double getWidth();
	
	public double getHeight();
	
	public double getDepth();
	
	public Point getSrcLocation();
}
