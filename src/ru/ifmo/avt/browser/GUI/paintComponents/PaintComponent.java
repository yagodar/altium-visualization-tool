package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class PaintComponent extends DraggedPaintComponent {

    private static final long serialVersionUID = -2493476665136976940L;

    public PaintComponent(Browserable browserable) {
	super();

	setBrowserable(browserable);
	setLocation(getBrowserable().getLocation());
	setSize(getBrowserable().getDimension());
	setPreferredSize(getBrowserable().getDimension());
	setToolTipText(getBrowserable().getDescription());
    }

    @Override
    public void setLocation(Point point) {

	getBrowserable().setLocation(point);
	super.setLocation(point);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2 = (Graphics2D) g;
	Shape shape = getBrowserable().getVisualizationShape();
	g2.setColor(getBrowserable().getColor());
	g2.fill(shape);
	g2.setColor(boardColor);
	g2.draw(shape);
    }

    public Browserable getBrowserable() {

	return browserable;
    }

    private void setBrowserable(Browserable browserable) {

	this.browserable = browserable;
    }

    public Point[] getPeak() {

	return getBrowserable().getPeak();
    }

    private Browserable browserable;
    private static Color boardColor = Color.BLACK;
}
