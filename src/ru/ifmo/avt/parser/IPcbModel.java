package ru.ifmo.avt.parser;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPcbModel {	
	public String getName();
	
	public ArrayList<Vertex> getVertices();
	
	public float getAbsWidth();
	
	public float getAbsHeight();
	
	public ArrayList<IPcbLayer> getLayers();
	
	public float getDepth();
	
	public ArrayList<IPcbElementModel> getElements();
	
	public ArrayList<HashMap<String, String>> getAdditionalProps(String propsType);
}
