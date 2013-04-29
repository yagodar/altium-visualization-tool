package ru.ifmo.avt.parser;

public interface IPcbDocParser {
	public IPcbObject createPcbObject(String pathToPcbDocFile);
	
	public IPcbObject getPcbObject();
}
