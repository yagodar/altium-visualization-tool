package ru.ifmo.avt;

import java.io.File;

import ru.ifmo.avt.parser.AltiumPcbDocParser;
import ru.ifmo.avt.parser.PcbModel;

class ParserTestLauncher {
	public static void main(String[] args) {
		//String testPath = "./data/data-in/empty.PcbDoc";
		String testPath = "./data/data-in/fill.PcbDoc";
		
		System.out.println(ParserTestLauncher.class.getSimpleName() + ":START:" + testPath);
		AltiumPcbDocParser.getInstance().createNewPcbModel(new File(testPath));
		PcbModel testPcbModel = AltiumPcbDocParser.getInstance().getPcbModel();

		System.out.println(ParserTestLauncher.class.getSimpleName() + ":STOP:" + testPcbModel);
	}
}
