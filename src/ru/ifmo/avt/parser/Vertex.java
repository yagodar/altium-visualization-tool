package ru.ifmo.avt.parser;

public class Vertex {
	public int getId() {
		return id;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	protected void setX(float x) {
		this.x = x;
	}
	
	protected void setY(float y) {
		this.y = y;
	}
	
	protected Vertex(int id) {
		this(id, -1.0f, -1.0f);
	}
	
	protected Vertex(int id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	private float x;
	private float y;
	private final int id;
}
