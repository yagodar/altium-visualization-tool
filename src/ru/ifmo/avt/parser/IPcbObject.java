package ru.ifmo.avt.parser;

public interface IPcbObject {
	public void setParameter(String parameterName, String parameterValue);
	
	public String getParameter(String parameterName);
}
