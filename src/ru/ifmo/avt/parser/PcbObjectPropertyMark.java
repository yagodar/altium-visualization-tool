package ru.ifmo.avt.parser;

public enum PcbObjectPropertyMark {
	//Common
	WIDTH("Ширина, мил", 0.0),
	HEIGHT("Высота, мил", 0.0),
	DEPTH("Толщина, мил", 0.0),
	OBJ_TERMAL_CONDUCT("Теплопроводность, коэф.", 150.0),
	OBJ_TEMPERATURE("Температура, C", 20.0),
	
	//PcbModel
	NAME("Имя", "unknown"),
	ENV_TERMAL_CONDUCT("Теплопроводность окр. среды, коэф.", 0.026),
	ENV_TEMPERATURE("Температура окр. среды, C", 20.0),
	MODULE_ELASTICITY("Модуль упругости, Н/м^2", 30200000000.0),
	DENSITY("Плотность, кг/м^3", 2050.0),
	PUASSONS_COEF("Коэффициент Пуассона, коэф.", 0.22),
	WEIGHT_ALL_ELEMENTS("Масса всех элементов, кг", 0.0),
	FREQUENCY("Частота, Гц", 0.0),
	DISPLACEMENT("Вибросмещение, м", 0.0),
	OWN_FREQUENCY("Собственная частота, Гц", 0.0),
	MAX_DEFLECTION("Допустимый прогиб, м", 0.0),
	DEFLECTION("Прогиб, м", 0.0),
	
	//PcbElementModel
	PATTERN("Шаблон", "unknown"),
	DESIGNATOR("Обозначение", "unknown"),
	LIBRARY_REFERENCE("Библиотечное название", "unknown"),
	SRC_DESCRIPTION("Описание", "unknown"),
	FOOTPRINT_DESCRIPTION("Описание фотошаблона", "unknown"),
	POWER("Мощность, Вт", 0.0),
	PERMISSIBLE_LOAD("Допустимая нагрузка, g", 20.0),
	VIBRO_ACCELERAION("Виброускорение, м/с^2", 0.0),
	
	;
	
	private PcbObjectPropertyMark(String name, Object defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	private final String name;
	private final Object defaultValue;
}
