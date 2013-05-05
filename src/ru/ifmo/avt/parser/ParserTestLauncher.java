package ru.ifmo.avt.parser;

import java.io.File;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class ParserTestLauncher {
	public static void main(String[] args) {
		//String testPath = "./data/data-in/empty.PcbDoc";
		String testPath = "./data/data-in/fill.PcbDoc";
		
		System.out.println(ParserTestLauncher.class.getSimpleName() + ":START:" + testPath);
		Browserable testPcbModel = AltiumPcbDocParser.getInstance().createPcbModel(new File(testPath));
		System.out.println(ParserTestLauncher.class.getSimpleName() + ":STOP:" + testPcbModel);
	}
}
