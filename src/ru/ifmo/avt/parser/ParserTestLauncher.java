package ru.ifmo.avt.parser;

import java.io.File;

import ru.ifmo.avt.parser.AltiumPcbDocParser;

public class ParserTestLauncher {
	public static void main(String[] args) {
		System.out.println(ParserTestLauncher.class.getSimpleName() + ":START");
		
		PcbModel testPcbModel = AltiumPcbDocParser.getInstance().createPcbModel(new File("./data/data-in/empty.PcbDoc"));
		testPcbModel = AltiumPcbDocParser.getInstance().createPcbModel(new File("./data/data-in/fill.PcbDoc"));
		
		System.out.println(ParserTestLauncher.class.getSimpleName() + ":STOP");
	}
}
