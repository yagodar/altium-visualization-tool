package ru.ifmo.avt.browser.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.List;

public interface Browserable {

    public Point[] getPeak();
    
    public List<Propertiable> getProperties();

    public Shape getVisualizationShape();
    
    public Color getColor();

    public List<Browserable> getBrowserableObjects();

    public String getDescription();

    public Point getLocation();

    public Dimension getDimension();

    public void setLocation(Point point);

    public boolean isBoard();
}
