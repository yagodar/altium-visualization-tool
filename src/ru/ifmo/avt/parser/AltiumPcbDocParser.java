package ru.ifmo.avt.parser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AltiumPcbDocParser {
	public static AltiumPcbDocParser getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AltiumPcbDocParser();
		}
		
		return INSTANCE;
	}
	
	public PcbModel createPcbObject(File pcbDocFile) {
		pcbModel = null;
		
		if(pcbDocFile != null) {
			if(pcbDocFile.isFile()) {
				pcbModel = parsePcbDocFile(pcbDocFile);
			}
			else {
				System.out.println("Error! pcbDocFile is not file! path: " + pcbDocFile.getAbsolutePath());
			}
		}
		
		return pcbModel;
	}

	public PcbModel getPcbModel() {
		return pcbModel;
	}
	
	private AltiumPcbDocParser() {}
	
	private PcbModel parsePcbDocFile(File pcbDocFile) {
		PcbModel newPcbModel = null;
		
		try {
			BufferedReader bufFileReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(pcbDocFile)), PCB_DOC_ENCODING));
			
			String pcbDocFileStr;
			newPcbModel = new PcbModel();
			
			while((pcbDocFileStr = bufFileReader.readLine()) != null) {
				if(pcbDocFileStr.startsWith("|RECORD=")) {
					
					String strParameters[] = pcbDocFileStr.split("|");					
					if(strParameters.length > 1) {
						
						String strParameterKeyValue[] = strParameters[0].split("=");
						if(strParameterKeyValue.length == 2) {
							
							String propsType = strParameterKeyValue[1];
							
							HashMap<String, String> props = new HashMap<String, String>();
							for (int i = 1; i < strParameters.length; i++) {
								strParameterKeyValue = strParameters[i].split("=");
								if(strParameterKeyValue.length == 2) {
									props.put(strParameterKeyValue[0], strParameterKeyValue[1]);
								}
							}
							
							newPcbModel.addAdditionalProps(propsType, props);
							
							if(propsType.equals("Board")) {
								for (String propKey : props.keySet()) {
									if(propKey != null) {
										String propValue = props.get(propKey);
										
										if(propKey.startsWith("VX") || propKey.startsWith("VY")) {
											try {
												int vertexId = Integer.parseInt(propKey.substring(2));
												float coord = Float.parseFloat(propValue.substring(0, propValue.lastIndexOf("mil")));
												
												Vertex boardVertex = newPcbModel.getVertex(vertexId);
												if(boardVertex == null) {
													boardVertex = new Vertex(vertexId);
													newPcbModel.addVertex(vertexId, boardVertex);
												}
												
												if(propKey.startsWith("VX")) {
													boardVertex.setX(coord);
												}
												else if(propKey.startsWith("VY")) {
													boardVertex.setY(coord);
												}
												
												props.remove(propKey);
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
										else if(propKey.startsWith("LAYER") && propKey.length() > "LAYER".length()) {
											if(propKey.endsWith("MECHENABLED")) {
												try {
													int layerId = Integer.parseInt(propKey.substring("LAYER".length(), propKey.lastIndexOf("MECHENABLED")));
													PcbLayer boardLayer = newPcbModel.getLayer(layerId);
													
													if(boardLayer == null) {
														boardLayer = new PcbLayer(layerId);
													}
													
													boardLayer.setEnabled(Boolean.parseBoolean(propValue));
													
													props.remove(propKey);
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
											else if(propKey.endsWith("DIELHEIGHT")) {
												try {
													int layerId = Integer.parseInt(propKey.substring("LAYER".length(), propKey.lastIndexOf("DIELHEIGHT")));

													//TODO
													
													props.remove(propKey);
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
											else if(propKey.endsWith("COPTHICK")) {
												try {
													int layerId = Integer.parseInt(propKey.substring("LAYER".length(), propKey.lastIndexOf("COPTHICK")));

													//TODO
													
													props.remove(propKey);
												}
												catch(Exception e) {
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
							else {
								
							}
						}
					}
				}
			}
			
			bufFileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return newPcbModel;
	}
	
	private PcbModel pcbModel;
	
	private static AltiumPcbDocParser INSTANCE;
	private static final String PCB_DOC_ENCODING = "utf8";
}
