package ru.ifmo.avt.parser;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.tca.IPcbModelForTca;

public class AltiumPcbDocParser {
	public static AltiumPcbDocParser getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AltiumPcbDocParser();
		}
		
		return INSTANCE;
	}
	
	public void createNewPcbModel(File pcbDocFile) {
		pcbModel = null;
		
		if(pcbDocFile != null) {
			if(pcbDocFile.isFile()) {
				pcbModel = parsePcbDocFile(pcbDocFile);
			}
			else {
				System.out.println("Error! pcbDocFile is not file! path: " + pcbDocFile.getAbsolutePath());
			}
		}
	}

	public PcbModel getPcbModel() {
		return pcbModel;
	}
	
	public Browserable getPcbModelBrowserable() {
		return pcbModel;
	}
	
	public IPcbModelForTca getPcbModelForTca() {
		return pcbModel;
	}
	
	public ArrayList<HashMap<String, String>> getOverAllProps(String propsType) {
		return overAllProps.get(propsType);
	}
	
	public Collection<ArrayList<HashMap<String, String>>> getAllOverAllProps() {
		return overAllProps.values();
	}
	
	private AltiumPcbDocParser() {
		overAllProps = new HashMap<String, ArrayList<HashMap<String, String>>>();
	}
	
	private PcbModel parsePcbDocFile(File pcbDocFile) {
		PcbModel newPcbModel = null;
		
		try {
			BufferedReader bufFileReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(pcbDocFile)), PCB_DOC_ENCODING));
			
			String pcbDocFileStr;
			newPcbModel = new PcbModel();
			
			while((pcbDocFileStr = bufFileReader.readLine()) != null) {
				if(pcbDocFileStr.startsWith("|RECORD=") || pcbDocFileStr.startsWith("RECORD=")) {
					
					String strParameters[] = pcbDocFileStr.split("[/|]");					
					int propsTypeIndx = 0;
					
					while(strParameters.length > propsTypeIndx && strParameters[propsTypeIndx].isEmpty()) {
						propsTypeIndx++;
					}
										
					if(strParameters.length > propsTypeIndx + 1) {
						String strParameterKeyValue[] = strParameters[propsTypeIndx].split("=");
						if(strParameterKeyValue.length == 2) {
							
							String propsType = strParameterKeyValue[1];
							
							HashMap<String, String> props = new HashMap<String, String>();
							for (int i = propsTypeIndx + 1; i < strParameters.length; i++) {
								strParameterKeyValue = strParameters[i].split("=");
								if(strParameterKeyValue.length == 2) {
									props.put(strParameterKeyValue[0], strParameterKeyValue[1]);
								}
							}
							
							addAdditionalProps(propsType, props);
							
							if(propsType.equals("Board")) {
								for (String propKey : props.keySet()) {
									if(propKey != null) {
										String propValue = props.get(propKey);
										
										if(propKey.equals("FILENAME")) {
											newPcbModel.setName(propValue.substring(propValue.lastIndexOf("\\") + 1, propValue.lastIndexOf(".")));
										}
										else if(propKey.startsWith("VX") || propKey.startsWith("VY")) {
											try {
												int vertexId = Integer.parseInt(propKey.substring(2));
												double coord = Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil")));
												
												PcbObjectVertex boardVertex = newPcbModel.getVertex(vertexId);
												if(boardVertex == null) {
													boardVertex = new PcbObjectVertex(vertexId);
													newPcbModel.addVertex(vertexId, boardVertex);
												}
												
												if(propKey.startsWith("VX")) {
													boardVertex.setLocation(coord, boardVertex.getY());
												}
												else if(propKey.startsWith("VY")) {
													boardVertex.setLocation(boardVertex.getX(), coord);
												}
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
										else if(propKey.startsWith("LAYER") && propKey.length() > "LAYER".length()) {
											if(propKey.endsWith("MECHENABLED")) {
												try {
													String layerMark = propKey.substring("LAYER".length(), propKey.lastIndexOf("MECHENABLED"));
													PcbLayer boardLayer = newPcbModel.getLayer(layerMark);
													
													if(boardLayer == null) {
														boardLayer = new PcbLayer(layerMark);
														newPcbModel.addLayer(layerMark, boardLayer);
													}
													
													boardLayer.setEnabled(Boolean.parseBoolean(propValue));
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
											else if(propKey.endsWith("DIELHEIGHT")) {
												try {
													String layerMark = propKey.substring("LAYER".length(), propKey.lastIndexOf("DIELHEIGHT"));

													PcbLayer boardLayer = newPcbModel.getLayer(layerMark);
													
													if(boardLayer == null) {
														boardLayer = new PcbLayer(layerMark);
														newPcbModel.addLayer(layerMark, boardLayer);
													}
													
													boardLayer.setDepth(boardLayer.getDepth() + Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil"))));
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
											else if(propKey.endsWith("COPTHICK")) {
												try {
													String layerMark = propKey.substring("LAYER".length(), propKey.lastIndexOf("COPTHICK"));

													PcbLayer boardLayer = newPcbModel.getLayer(layerMark);
													
													if(boardLayer == null) {
														boardLayer = new PcbLayer(layerMark);
														newPcbModel.addLayer(layerMark, boardLayer);
													}
													
													boardLayer.setDepth(boardLayer.getDepth() + Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil"))));
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
											else if(propKey.endsWith("DIELMATERIAL")) {
												try {
													String layerMark = propKey.substring("LAYER".length(), propKey.lastIndexOf("DIELMATERIAL"));

													PcbLayer boardLayer = newPcbModel.getLayer(layerMark);
													
													if(boardLayer == null) {
														boardLayer = new PcbLayer(layerMark);
														newPcbModel.addLayer(layerMark, boardLayer);
													}
													
													boardLayer.setMaterialName(propValue);
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
										}
										else if(propKey.equals("TOPHEIGHT")) {
											try {
												PcbLayer boardLayer = newPcbModel.getLayer(PcbLayer.TOP_LAYER_MARK);
												
												if(boardLayer == null) {
													boardLayer = new PcbLayer(PcbLayer.TOP_LAYER_MARK);
													newPcbModel.addLayer(PcbLayer.TOP_LAYER_MARK, boardLayer);
												}
												
												boardLayer.setDepth(Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil"))));
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
										else if(propKey.equals("BOTTOMHEIGHT")) {
											try {
												PcbLayer boardLayer = newPcbModel.getLayer(PcbLayer.BOTTOM_LAYER_MARK);
												
												if(boardLayer == null) {
													boardLayer = new PcbLayer(PcbLayer.BOTTOM_LAYER_MARK);
													newPcbModel.addLayer(PcbLayer.BOTTOM_LAYER_MARK, boardLayer);
												}
												
												boardLayer.setDepth(Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil"))));
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
										else if(propKey.equals("TOPMATERIAL")) {
											try {
												PcbLayer boardLayer = newPcbModel.getLayer(PcbLayer.TOP_LAYER_MARK);
												
												if(boardLayer == null) {
													boardLayer = new PcbLayer(PcbLayer.TOP_LAYER_MARK);
													newPcbModel.addLayer(PcbLayer.TOP_LAYER_MARK, boardLayer);
												}
												
												boardLayer.setMaterialName(propValue);
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}										
										else if(propKey.equals("BOTTOMMATERIAL")) {
											try {
												PcbLayer boardLayer = newPcbModel.getLayer(PcbLayer.BOTTOM_LAYER_MARK);
												
												if(boardLayer == null) {
													boardLayer = new PcbLayer(PcbLayer.BOTTOM_LAYER_MARK);
													newPcbModel.addLayer(PcbLayer.BOTTOM_LAYER_MARK, boardLayer);
												}
												
												boardLayer.setMaterialName(propValue);
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
							else if(propsType.equals("Component")) {
								try {
									int elementId = Integer.parseInt(props.get("ID"));

									PcbElementModel element = newPcbModel.getElement(elementId);
									if(element == null) {
										element = new PcbElementModel(elementId);
										newPcbModel.addElement(elementId, element);
									}
									
									try {
										element.setSrcLocX(Double.parseDouble(props.get("X").substring(0, props.get("X").lastIndexOf("mil"))));
										element.setSrcLocY(Double.parseDouble(props.get("Y").substring(0, props.get("Y").lastIndexOf("mil"))));
									}
									catch(Exception e) {
										e.printStackTrace();
									}
									
									element.setPatternName(props.get("PATTERN"));

									element.setDesignatorName(props.get("SOURCEDESIGNATOR"));

									element.setLibraryReference(props.get("SOURCELIBREFERENCE"));

									element.setDescription(props.get("SOURCEDESCRIPTION"));

									element.setFootprintDescription(props.get("FOOTPRINTDESCRIPTION"));
								}
								catch(Exception e) {
									e.printStackTrace();
								}
							}
							else if(propsType.equals("ComponentBody")) {
								try {
									int elementId = Integer.parseInt(props.get("COMPONENT"));
									
									PcbElementModel element = newPcbModel.getElement(elementId);
									if(element == null) {
										element = new PcbElementModel(elementId);
										newPcbModel.addElement(elementId, element);
									}
									
									element.setDepth(Double.parseDouble(props.get("OVERALLHEIGHT").substring(0, props.get("OVERALLHEIGHT").lastIndexOf("mil"))));
									
									for (String propKey : props.keySet()) {
										if(propKey != null) {
											String propValue = props.get(propKey);
											
											if(propKey.startsWith("VX") || propKey.startsWith("VY")) {
												try {
													int vertexId = Integer.parseInt(propKey.substring(2));
													double coord = Double.parseDouble(propValue.substring(0, propValue.lastIndexOf("mil")));
													
													PcbObjectVertex elementVertex = element.getVertex(vertexId);
													if(elementVertex == null) {
														elementVertex = new PcbObjectVertex(vertexId);
														element.addVertex(vertexId, elementVertex);
													}
													
													if(propKey.startsWith("VX")) {
														elementVertex.setLocation(coord, elementVertex.getY());
													}
													else if(propKey.startsWith("VY")) {
														elementVertex.setLocation(elementVertex.getX(), coord);
													}
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
										}
									}
								}
								catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
			
			bufFileReader.close();
			
			newPcbModel.getName();
			newPcbModel.getWidth();
			newPcbModel.getHeight();
			newPcbModel.getDepth();
			newPcbModel.getThermalConduct();
			newPcbModel.getTemperature();
			newPcbModel.getEnvThermalConduct();
			newPcbModel.getEnvTemperature();
		
			newPcbModel.setSrcLocation(new Point());
			
			Point boardLocation = new Point();
			boardLocation.setLocation(PcbModel.DEFAULT_VIEW_LOC_X, PcbModel.DEFAULT_VIEW_LOC_Y);
			newPcbModel.setLocation(boardLocation);
			
			for (PcbElementModel element : newPcbModel.getAllElements()) {
				element.getWidth();
				element.getHeight();
				element.getDepth();
				element.getThermalConduct();
				element.getTemperature();
				element.getPower();
				element.getPatternName();
				element.getDesignatorName();
				element.getLibraryReference();
				element.getSrcDescription();
				element.getFootprintDescription();
				
				Point elementLocation = new Point();
				
				double elementMinX = element.getVertecesMinX();
				double elementMaxY = element.getVertecesMaxY();
				
				if(elementMinX != 0.0 && elementMaxY != 0.0) {
					elementLocation.setLocation(elementMinX - newPcbModel.getVertecesMinX(), newPcbModel.getVertecesMaxY() - elementMaxY);
				}
				else {
					elementLocation.setLocation(element.getSrcLocX() - PcbElementModel.DEFAULT_WIDTH / 2.0 - newPcbModel.getVertecesMinX(), newPcbModel.getVertecesMaxY() - element.getSrcLocY() - PcbElementModel.DEFAULT_HEIGHT / 2.0);
					element.setWidth(PcbElementModel.DEFAULT_WIDTH);
					element.setHeight(PcbElementModel.DEFAULT_HEIGHT);
				}
				
				element.setLocation(elementLocation);
				
				Point elementSrcLocation = new Point();
				elementSrcLocation.setLocation(elementLocation.getX(),elementLocation.getY());
				element.setSrcLocation(elementSrcLocation);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return newPcbModel;
	}
	
	private void addAdditionalProps(String propsType, HashMap<String, String> props) {
		if(propsType != null && !propsType.isEmpty() && props != null) {
			if(overAllProps.get(propsType) == null) {
				overAllProps.put(propsType, new ArrayList<HashMap<String, String>>());
			}
			
			overAllProps.get(propsType).add(props);
		}
	}

	private PcbModel pcbModel;
	private final HashMap<String, ArrayList<HashMap<String, String>>> overAllProps;
	
	private static AltiumPcbDocParser INSTANCE;
	
	private static final String PCB_DOC_ENCODING = "windows-1251";
}
