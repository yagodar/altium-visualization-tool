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
import ru.ifmo.avt.vca.IPcbObjectModelForVca;

abstract class AbstractPcbObject implements Browserable, IPcbObjectModelForTca, IPcbObjectModelForVca {
	@Override
	public Point[] getPeak() {
		return new Point[] { getLeftTopPeakPoint(), getRightBottomPeakPoint() };
	}
	
	@Override
	public List<Propertiable> getProperties() {
		List<Propertiable> propsList = new ArrayList<Propertiable>();
		propsList.addAll(getAllPropertiesByMarks().values());
		return propsList;
	}
	
	@Override
	public Shape getVisualizationShape() {
		return new Rectangle2D.Double(getLeftTopPeakPoint().getX(), getLeftTopPeakPoint().getY(), getRightBottomPeakPoint().getX() - getLeftTopPeakPoint().getX() - 1.0, getRightBottomPeakPoint().getY() - getLeftTopPeakPoint().getY() - 1.0);
	}
	
	@Override
	public Point getSrcLocation() {
		return srcLocation;
	}
	
	@Override
	public void setSrcLocation(Point srcLocation) {
		this.srcLocation = srcLocation;
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
		return new Dimension((int) (getRightBottomPeakPoint().getX() - getLeftTopPeakPoint().getX()), (int) (getRightBottomPeakPoint().getY() - getLeftTopPeakPoint().getY()));
	}
	
	@Override
	public double getThermalConduct() {
		return (double) getProperty(PcbObjectPropertyMark.OBJ_TERMAL_CONDUCT);
	}
	
	@Override
	public double getTemperature() {
		return (double) getProperty(PcbObjectPropertyMark.OBJ_TEMPERATURE);
	}
	
	@Override
	public void setTemperature(double temperature) {
		setProperty(PcbObjectPropertyMark.OBJ_TEMPERATURE, temperature);
	}
	
	@Override
	public double getWidth() {
		return (double) getProperty(PcbObjectPropertyMark.WIDTH);
	}
	
	@Override
	public void setWidth(double value) {
		setProperty(PcbObjectPropertyMark.WIDTH, value);
	}
	
	@Override
	public double getHeight() {
		return (double) getProperty(PcbObjectPropertyMark.HEIGHT);
	}
	
	@Override
	public void setHeight(double value) {
		setProperty(PcbObjectPropertyMark.HEIGHT, value);
	}
	
	@Override
	public double getDepth() {
		return (double) getProperty(PcbObjectPropertyMark.DEPTH);
	}
	
	public void setThermalConduct(double thermalConduct) {
		setProperty(PcbObjectPropertyMark.OBJ_TERMAL_CONDUCT, thermalConduct);
	}
	
	public void setDepth(double value) {
		setProperty(PcbObjectPropertyMark.DEPTH, value);
	}
	
	protected AbstractPcbObject() {
		propsByMark = new HashMap<String, Propertiable>();
		vertices = new HashMap<Integer, PcbObjectVertex>();
	}
	
	protected double getSrcLocX() {
		return srcLocX;
	}

	protected void setSrcLocX(double srcLocX) {
		this.srcLocX = srcLocX;
	}

	protected double getSrcLocY() {
		return srcLocY;
	}

	protected void setSrcLocY(double srcLocY) {
		this.srcLocY = srcLocY;
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
	
	protected Object getProperty(PcbObjectPropertyMark mark) {
		Object value = mark.getDefaultValue();

		Propertiable property = getPropertyByMark(mark.toString());
		if(property != null) {
			value = property.getValue();
		}
		else {
			setProperty(mark, value);
		}
		
		return value;
	}
	
	protected void setProperty(PcbObjectPropertyMark mark, Object value) {
		Propertiable property = getPropertyByMark(mark.toString());
		
		if(property == null) {
			property = new PcbObjectProperty(this, mark.getName(), value);
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

	protected Point getLeftTopPeakPoint() {
		if(leftTopPoint == null) {
			leftTopPoint = new Point(0, 0);
		}
		
		return leftTopPoint;
	}
	
	protected Point getRightBottomPeakPoint() {
		if(rightBottomPoint == null) {
			rightBottomPoint = new Point();
		}
		
		return rightBottomPoint;
	}
	
	private Point srcLocation;
	private Point location;
	Point leftTopPoint;
	Point rightBottomPoint;
	private HashMap<String, Propertiable> propsByMark;
	private double srcLocX;
	private double srcLocY;
	private HashMap<Integer, PcbObjectVertex> vertices;
}
