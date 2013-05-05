package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class PaintComponent extends JComponent {
    
    private static final long serialVersionUID = -2493476665136976940L;

    public PaintComponent(Browserable browserable) {
	
	super();
	this.browserable = browserable;
	setLayout(null);
	setLocation(browserable.getLocation());
	setSize(browserable.getDimension());
	setPreferredSize(browserable.getDimension());
	setToolTipText(browserable.getDescription());
	
	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mousePressed(MouseEvent event) {
		
		if (getBrowserable().getVisualizationShape().contains(event.getPoint())) {
		    ((Component) event.getSource()).getParent().dispatchEvent(event);
		}
	    }
	});

	addMouseMotionListener(new MouseAdapter() {
	    
	    Point oldLocation;

	    public void mouseMoved(java.awt.event.MouseEvent e) {
		
		oldLocation = e.getPoint();
	    }

	    public void mouseDragged(java.awt.event.MouseEvent e) {
		
		Point newLocation = e.getPoint();

		Point point = new Point(newLocation.x - (oldLocation.x - getX()), newLocation.y - (oldLocation.y - getY()));

		setLocation(point);
		
		((Component)e.getSource()).getParent().repaint();
	    }

	});
    };

    @Override
    public void setLocation(Point point) {
	
	browserable.setLocation(point);
	super.setLocation(point);
    }

    public void paintComponent(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	g2.draw(browserable.getVisualizationShape());
    }

    public Browserable getBrowserable() {
	
	return browserable;
    }

    public Point[] getPeak() {
	
	return getBrowserable().getPeak();
    }

    private Browserable browserable;
}
