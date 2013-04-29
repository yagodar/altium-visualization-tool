package ru.ifmo.avt.parser;

import java.util.HashMap;

public class PcbObject implements IPcbObject {
	@Override
	public void setParameter(String parameterName, String parameterValue) {
		pcbObjectParameters.put(parameterName, parameterValue);
	}

	@Override
	public String getParameter(String parameterName) {
		return pcbObjectParameters.get(parameterName);
	}
	
	protected PcbObject() {
		pcbObjectParameters = new HashMap<String, String>();
	}
	
	private final HashMap<String, String> pcbObjectParameters;
}
