package ru.ifmo.avt.parser;

enum PcbObjectPropertyMark {
	//Common
	WIDTH("Ширина"),
	HEIGHT("Высота"),
	DEPTH("Толщина"),
	MATERIAL("Материал"),
	
	//PcbModel
	NAME("Имя"),
	
	//PcbElementModel
	PATTERN("Шаблон"),
	DESIGNATOR("Обозначение"),
	LIBRARY_REFERENCE("Библиотечное название"),
	DESCRIPTION("Описание"),
	FOOTPRINT_DESCRIPTION("Описание фотошаблона"),
	
	;
	
	private PcbObjectPropertyMark(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private final String name;
}
