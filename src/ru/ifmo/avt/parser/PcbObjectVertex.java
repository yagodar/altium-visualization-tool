package ru.ifmo.avt.parser;

import java.awt.Point;

class PcbObjectVertex extends Point {
	public int getId() {
		return id;
	}
		
	protected PcbObjectVertex(int id) {
		this(id, -1, -1);
	}
	
	protected PcbObjectVertex(int id, double x, double y) {
		super();
		
		this.id = id;
		setLocation(x, y);
	}
	
	private final int id;
	private static final long serialVersionUID = 9182201232398656607L;
}
