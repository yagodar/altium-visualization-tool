package ru.ifmo.avt.browser.tempInstance;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;

public class BrowserableObject implements Browserable {

	public BrowserableObject() {
		list = new ArrayList<Propertiable>();
		list.add(new PropertiableObject("свойство 1", true));
		list.add(new PropertiableObject("свойство 1.5", false));
		list.add(new PropertiableObject("свойство 2", "ololo"));
		list.add(new PropertiableObject("свойство 3", true));
		list.add(new PropertiableObject("свойство 3.5", false));
	}

	@Override
	public List<Propertiable> getProperties() {
		return list;
	}

	@Override
	public int getCountPeak() {
		return 2;
	}

	@Override
	public Point2D[] getPeak() {
		Point2D[] points = { new Point(10, 100), new Point(250, 300) };
		return points;
	}

	@Override
	public Shape makeShape(Point2D[] points) {
		RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
		shape.setFrameFromDiagonal(points[0], points[1]);
		return shape;
	}

	private List<Propertiable> list;
}
