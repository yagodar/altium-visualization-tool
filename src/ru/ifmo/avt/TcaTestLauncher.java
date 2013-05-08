package ru.ifmo.avt;

import java.io.File;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;
import ru.ifmo.avt.parser.AltiumPcbDocParser;
import ru.ifmo.avt.parser.PcbModel;
import ru.ifmo.avt.parser.PcbObjectPropertyMark;
import ru.ifmo.avt.tca.PcbThermalConditionsAnalyzer;


class TcaTestLauncher {
	public static void main(String[] args) {
		//String testPath = "./data/data-in/empty.PcbDoc";
		String testPath = "./data/data-in/fill.PcbDoc";

		System.out.println(ParserTestLauncher.class.getSimpleName() + ":START_0:" + testPath);
		AltiumPcbDocParser.getInstance().createNewPcbModel(new File(testPath));
		PcbModel testPcbModel = AltiumPcbDocParser.getInstance().getPcbModel();
		
		chargeElements(testPcbModel);
		
		System.out.println(TcaTestLauncher.class.getSimpleName() + ":START_1:" + testPcbModel);
		PcbThermalConditionsAnalyzer.getInstance().analyzePcbModel(testPcbModel);

		System.out.println(TcaTestLauncher.class.getSimpleName() + ":STOP");
	}
	
	private static void chargeElements(PcbModel testPcbModel) {
		for (Browserable element : testPcbModel.getBrowserableObjects()) {
			for (Propertiable prop : element.getProperties()) {
				if(prop.getName().equals(PcbObjectPropertyMark.POWER.getName())) {
					prop.setValue(20.0);
				}
			}
		}
	}
}
