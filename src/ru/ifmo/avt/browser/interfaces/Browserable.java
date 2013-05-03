package ru.ifmo.avt.browser.interfaces;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.List;

public interface Browserable {

	public List<Propertiable> getProperties();

	public int getCountPeak();

	public Point2D[] getPeak();

	public Shape makeShape(Point2D[] points);
}
