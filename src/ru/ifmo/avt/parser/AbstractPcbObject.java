package ru.ifmo.avt.parser;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;
import ru.ifmo.avt.tca.IPcbObjectModelForTca;

public abstract class AbstractPcbObject implements Browserable, IPcbObjectModelForTca {
	@Override
	public Point[] getPeak() {
		Point leftTopPoint = new Point();
		leftTopPoint.setLocation(0.0, 0.0);
		
		Point rightBottomPoint = new Point();
		rightBottomPoint.setLocation(getWidth(), getHeight());
		
		return new Point[] { leftTopPoint, rightBottomPoint };
	}
	
	@Override
	public List<Propertiable> getProperties() {
		List<Propertiable> propsList = new ArrayList<Propertiable>();
		propsList.addAll(getAllPropertiesByMarks().values());
		return propsList;
	}
	
	@Override
	public Shape getVisualizationShape() {
		return new Rectangle2D.Double(0.0, 0.0, getWidth(), getHeight());
	}
	
	@Override
	public Point getLocation() {
		return location;
	}
	
	@Override
    public void setLocation(Point location) {
		this.location = location;
    }
	
	@Override
	public Dimension getDimension() {
		return new Dimension((int) getWidth() + 10, (int) getHeight() + 10);
	}
	
	@Override
	public double getThermalConduct() {
		double thermalConduct = DEFAULT_TERMAL_CONDACT;
		
		Propertiable thermalConductProperty = getPropertyByMark(PcbObjectPropertyMark.OBJ_TERMAL_CONDUCT.toString());
		if(thermalConductProperty != null) {
			thermalConduct = (double) thermalConductProperty.getValue();
		}
		else {
			setThermalConduct(thermalConduct);
		}
		
		return thermalConduct;
	}
	
	@Override
	public double getTemperature() {
		double value = DEFAULT_TEMPERATURE;
		
		Propertiable valueProperty = getPropertyByMark(PcbObjectPropertyMark.OBJ_TEMPERATURE.toString());
		if(valueProperty != null) {
			value = (double) valueProperty.getValue();
		}
		else {
			setTemperature(value);
		}
		
		return value;
	}
	
	@Override
	public void setTemperature(double temperature) {
		setProperty(PcbObjectPropertyMark.OBJ_TEMPERATURE, temperature);
	}
	
	public void setThermalConduct(double thermalConduct) {
		setProperty(PcbObjectPropertyMark.OBJ_TERMAL_CONDUCT, thermalConduct);
	}
	
	public double getWidth() {
		double width = 0.0;
		
		Propertiable widthProperty = getPropertyByMark(PcbObjectPropertyMark.WIDTH.toString());
		if(widthProperty != null) {
			width = (double) widthProperty.getValue();
		}
		else {
			width = getVertecesMaxX() - getVertecesMinX();
			setWidth(width);
		}
		
		return width;
	}
	
	public void setWidth(double width) {
		setProperty(PcbObjectPropertyMark.WIDTH, width);
	}
	
	public double getHeight() {
		double height = 0.0;

		Propertiable absHeightProperty = getPropertyByMark(PcbObjectPropertyMark.HEIGHT.toString());
		if(absHeightProperty != null) {
			height = (double) absHeightProperty.getValue();
		}
		else {
			height = getVertecesMaxY() - getVertecesMinY();
			setHeight(height);
		}
		
		return height;
	}

	public void setHeight(double height) {
		setProperty(PcbObjectPropertyMark.HEIGHT, height);
	}
	
	abstract public double getDepth();
	
	public void setDepth(double depth) {
		setProperty(PcbObjectPropertyMark.DEPTH, depth);
	}
	
	protected AbstractPcbObject() {
		propsByMark = new HashMap<String, Propertiable>();
		
		location = new Point(0, 0);
		vertices = new HashMap<Integer, PcbObjectVertex>();
	}
	
	protected PcbObjectVertex getVertex(int vertexId) {
		return vertices.get(vertexId);
	}
	
	protected Collection<PcbObjectVertex> getAllVertices() {
		return vertices.values();
	}
	
	protected void addVertex(int vertexId, PcbObjectVertex pcbObjectVertex) {
		vertices.put(vertexId, pcbObjectVertex);
	}
	
	protected Propertiable getPropertyByMark(String propMark) {
		return propsByMark.get(propMark);
	}
	
	protected HashMap<String, Propertiable> getAllPropertiesByMarks() {
		return propsByMark;
	}
	
	protected void setProperty(PcbObjectPropertyMark mark, Object value) {
		Propertiable property = getPropertyByMark(mark.toString());
		
		if(property == null) {
			property = new PcbObjectProperty(mark.getName(), value);
			propsByMark.put(mark.toString(), property);
		}
		else {
			property.setValue(value);
		}
	}
	
	protected double getVertecesMinX() {
		double minX = 0.0;
		
		PcbObjectVertex pcbObjectVertex;
		for (int i = 0; i < vertices.size(); i++) {
			pcbObjectVertex = (PcbObjectVertex) vertices.values().toArray()[i];

			if(i == 0) {
				minX = pcbObjectVertex.getX();				
			}

			if(minX > pcbObjectVertex.getX()) {
				minX = pcbObjectVertex.getX();
			}
		}
		
		return minX;
	}
	
	protected double getVertecesMinY() {
		double minY = 0.0;
		
		PcbObjectVertex pcbObjectVertex;
		for (int i = 0; i < vertices.size(); i++) {
			pcbObjectVertex = (PcbObjectVertex) vertices.values().toArray()[i];

			if(i == 0) {
				minY = pcbObjectVertex.getY();				
			}

			if(minY > pcbObjectVertex.getY()) {
				minY = pcbObjectVertex.getY();
			}
		}
		
		return minY;
	}
	
	protected double getVertecesMaxX() {
		double maxX = 0.0;
		
		PcbObjectVertex pcbObjectVertex;
		for (int i = 0; i < vertices.size(); i++) {
			pcbObjectVertex = (PcbObjectVertex) vertices.values().toArray()[i];

			if(i == 0) {
				maxX = pcbObjectVertex.getX();				
			}

			if(maxX < pcbObjectVertex.getX()) {
				maxX = pcbObjectVertex.getX();
			}
		}
		
		return maxX;
	}
	
	protected double getVertecesMaxY() {
		double maxY = 0.0;
		
		PcbObjectVertex pcbObjectVertex;
		for (int i = 0; i < vertices.size(); i++) {
			pcbObjectVertex = (PcbObjectVertex) vertices.values().toArray()[i];

			if(i == 0) {
				maxY = pcbObjectVertex.getY();				
			}

			if(maxY < pcbObjectVertex.getY()) {
				maxY = pcbObjectVertex.getY();
			}
		}
		
		return maxY;
	}
	
	private HashMap<String, Propertiable> propsByMark;
	private Point location;
	
	private HashMap<Integer, PcbObjectVertex> vertices;
	
	private static final double DEFAULT_TERMAL_CONDACT = 150;
	private static final double DEFAULT_TEMPERATURE = 20.0;
}
