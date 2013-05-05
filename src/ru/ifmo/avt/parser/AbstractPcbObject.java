package ru.ifmo.avt.parser;

import java.util.Collection;
import java.util.HashMap;

public abstract class AbstractPcbObject {
	public Vertex getVertex(int vertexId) {
		return vertices.get(vertexId);
	}
	
	public Collection<Vertex> getAllVertices() {
		return vertices.values();
	}
	
	public float getAbsWidth() {
		if(absWidth == -1.0f) {
			float minX = 0.0f;
			float maxX = 0.0f;
			
			Vertex vertex;
			for (int i = 0; i < vertices.size(); i++) {
				vertex = (Vertex) vertices.values().toArray()[i];
				
				if(i == 0) {
					minX = vertex.getX();
					maxX = vertex.getX();
				}
				
				if(minX > vertex.getX()) {
					minX = vertex.getX();
				}
				
				if(maxX < vertex.getX()) {
					maxX = vertex.getX();
				}
			}
			
			absWidth = maxX - minX;
		}
		
		return absWidth;
	}
	
	public float getAbsHeight() {
		if(absHeight == -1.0f) {
			float minY = 0.0f;
			float maxY = 0.0f;
			
			Vertex vertex;
			for (int i = 0; i < vertices.size(); i++) {
				vertex = (Vertex) vertices.values().toArray()[i];
				
				if(i == 0) {
					minY = vertex.getY();
					maxY = vertex.getY();
				}
				
				if(minY > vertex.getY()) {
					minY = vertex.getY();
				}
				
				if(maxY < vertex.getY()) {
					maxY = vertex.getY();
				}
			}
			
			absHeight = maxY - minY;
		}
		
		return absHeight;
	}
	
	protected void addVertex(int vertexId, Vertex vertex) {
		vertices.put(vertexId, vertex);
	}
	
	protected AbstractPcbObject() {
		vertices = new HashMap<Integer, Vertex>();
		absWidth = -1.0f;
		absHeight = -1.0f;
	}
	
	private HashMap<Integer, Vertex> vertices;
	private float absWidth;
	private float absHeight;
}
