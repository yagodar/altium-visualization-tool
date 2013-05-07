package ru.ifmo.avt.tca;

import java.io.File;

import ru.ifmo.avt.parser.AltiumPcbDocParser;
import ru.ifmo.avt.parser.ParserTestLauncher;


public class TcaTestLauncher {
	public static void main(String[] args) {
		//String testPath = "./data/data-in/empty.PcbDoc";
		String testPath = "./data/data-in/fill.PcbDoc";

		System.out.println(ParserTestLauncher.class.getSimpleName() + ":START_0:" + testPath);
		AltiumPcbDocParser.getInstance().createNewPcbModel(new File(testPath));
		IPcbModelForTca testPcbModel = AltiumPcbDocParser.getInstance().getPcbModelForTca();
		
		System.out.println(TcaTestLauncher.class.getSimpleName() + ":START_1:" + testPcbModel);
		PcbThermalConditionsAnalyzer.getInstance().analyzePcbModel(testPcbModel);

		System.out.println(TcaTestLauncher.class.getSimpleName() + ":STOP");
	}
}
