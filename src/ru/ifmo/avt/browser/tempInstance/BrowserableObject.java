package ru.ifmo.avt.browser.tempInstance;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    }

    @Override
    public List<Propertiable> getProperties() {
	return listProperties;
    }

    @Override
    public Point2D[] getPeak() {
	Point2D[] points = { new Point(generator.nextInt(10), generator.nextInt(100)), new Point(generator.nextInt(250), generator.nextInt(300)) };
	return points;
    }

    @Override
    public Shape makeShape(Point2D[] points) {
	RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
	shape.setFrameFromDiagonal(points[0], points[1]);
	return shape;
    }

    @Override
    public List<Browserable> getBrowserableObjects() {
	return listBrowserableObject;
    }

    public void setBrowserableObjects(List<Browserable> listBrowserableObjects) {
	this.listBrowserableObject = listBrowserableObjects;
    }

    private List<Propertiable> listProperties;
    private List<Browserable> listBrowserableObject;
    private static Random generator = new Random();
}
