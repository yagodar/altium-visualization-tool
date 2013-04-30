package ru.ifmo.avt.parser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AltiumPcbDocParser {
	public static IPcbDocParser getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AltiumPcbDocParser();
		}
		
		return INSTANCE;
	}
	
	@Override
	public IPcbObject createPcbObject(String pathToPcbDocFile) {
		pcbObject = null;
		
		try {
			File pcbDocFile = new File(pathToPcbDocFile);
			if(pcbDocFile.isFile()) {
				pcbObject = parsePcbDocFile(pcbDocFile);
			}
			else {
				System.out.println("Error! pcbDocFile is not file! path: pathToPcbDocFile");
			}
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		return pcbObject;
	}

	@Override
	public IPcbObject getPcbObject() {
		return pcbObject;
	}
	
	private AltiumPcbDocParser() {}
	
	private IPcbObject parsePcbDocFile(File pcbDocFile) {
		IPcbObject newPcbObject = null;
		
		try {
			BufferedReader bufFileReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(pcbDocFile)), PCB_DOC_ENCODING));
			
			String pcbDocFileStr;
			int lineNumber = 0;
			newPcbObject = new PcbObject();
			
			while((pcbDocFileStr = bufFileReader.readLine()) != null) {
				lineNumber++;
				
				if(pcbDocFileStr.startsWith("|RECORD=")) {
					
					String strParameters[] = pcbDocFileStr.split("|");					
					if(strParameters.length > 1) {
						
						String strParameterKeyValue[] = strParameters[0].split("=");
						if(strParameterKeyValue.length == 2) {
							
							String strParametersGroupName = strParameterKeyValue[1];						
							
							for (int i = 1; i < strParameters.length; i++) {
								strParameterKeyValue = strParameters[i].split("=");
								if(strParameterKeyValue.length == 2) {
									newPcbObject.setParameter(strParametersGroupName + ":" + lineNumber + ":" + strParameterKeyValue[0], strParameterKeyValue[1]);
								}
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
		
		return newPcbObject;
	}
	
	private IPcbObject pcbObject;
	
	private static IPcbDocParser INSTANCE;
	private static final String PCB_DOC_ENCODING = "utf8";
}
