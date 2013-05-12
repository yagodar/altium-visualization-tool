package ru.ifmo.avt.browser.tempInstance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;

public class BrowserableObject implements Browserable {

    public BrowserableObject() {
	listProperties = new ArrayList<Propertiable>();
	listProperties.add(new PropertiableObject("свойство 1", true));
	listProperties.add(new PropertiableObject("свойство 1.5", false));
	listProperties.add(new PropertiableObject("свойство 2", "ololo"));
	listProperties.add(new PropertiableObject("свойство 3", true));
	listProperties.add(new PropertiableObject("свойство 3.5", false));
	listProperties.add(new PropertiableObject("h", 100));
	listProperties.add(new PropertiableObject("w", 200));
    }

    @Override
    public List<Propertiable> getProperties() {
	return listProperties;
    }

    @Override
    public List<Browserable> getBrowserableObjects() {
	return listBrowserableObject;
    }
    
    @Override
    public String getDescription() {
	return "<html><H1>Это компонент</H1></html>";
    }

    public void setBrowserableObjects(List<Browserable> listBrowserableObjects) {
	this.listBrowserableObject = listBrowserableObjects;
    }
    
    @Override
    public Point[] getPeak() {
	return new Point[]  {new Point(0, 0), new Point(150, 50)};
    }
    
    @Override
    public Shape getVisualizationShape() {
	RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
	shape.setFrameFromDiagonal(getPeak()[0], getPeak()[1]);
	return shape;
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
	Dimension d = getVisualizationShape().getBounds().getSize();
	return new Dimension((int)d.getWidth() + 1, (int)d.getHeight() + 1);
    }

    private Point location = new Point(100, 100);
    private List<Propertiable> listProperties;
    private List<Browserable> listBrowserableObject;
    @Override
    public boolean isBoard() {
	return true;
    }
    @Override
    public Color getColor() {
	// TODO Auto-generated method stub
	return null;
    }
}
