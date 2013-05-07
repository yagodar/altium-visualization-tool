package ru.ifmo.avt.parser;

enum PcbObjectPropertyMark {
	//Common
	WIDTH("Ширина, мил"),
	HEIGHT("Высота, мил"),
	DEPTH("Толщина, мил"),
	OBJ_TERMAL_CONDUCT("Теплопроводность, коэф."),
	
	//PcbModel
	NAME("Имя"),
	ENV_TERMAL_CONDUCT("Теплопроводность окр. среды, коэф."),
	ENV_TEMPERATURE("Температура окр. среды, C"),
	
	//PcbElementModel
	PATTERN("Шаблон"),
	DESIGNATOR("Обозначение"),
	LIBRARY_REFERENCE("Библиотечное название"),
	DESCRIPTION("Описание"),
	FOOTPRINT_DESCRIPTION("Описание фотошаблона"),
	POWER("Мощность, Вт"),
	
	;
	
	private PcbObjectPropertyMark(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private final String name;
}
