package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import org.eclipse.emf.codegen.ecore.templates.editor.EntryPoint;

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
	if(this instanceof ElementPaintComponent)
	    System.out.println("");
	
	Graphics2D g2 = (Graphics2D) g;
	Shape shape = getBrowserable().getVisualizationShape();
	setLocation(getBrowserable().getLocation());
	System.out.println("-->" + getSize());
	System.out.println("-->" + getPreferredSize());
	setSize(getBrowserable().getDimension());
	setPreferredSize(getBrowserable().getDimension());
	setToolTipText(getBrowserable().getDescription());
	g2.setColor(getBrowserable().getColor());
	g2.fill(shape);
	g2.setColor(borderColor);
	g2.draw(shape);
	
	super.paintComponent(g);
    }

    @Override
    public void repaint() {
        super.repaint();
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
    private static Color borderColor = Color.BLACK;
}
