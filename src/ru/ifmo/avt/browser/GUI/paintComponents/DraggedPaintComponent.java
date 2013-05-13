package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import javax.swing.JComponent;

public class DraggedPaintComponent extends JComponent {

    public DraggedPaintComponent() {
	addMouseMotionListener(new MouseAdapter() {

	    Point oldLocation;

	    public void mouseMoved(java.awt.event.MouseEvent event) {

		oldLocation = event.getPoint();
	    }

	    public void mouseDragged(java.awt.event.MouseEvent event) {

		Point newLocation = event.getPoint();

		Point point = new Point(getX() + (newLocation.x - oldLocation.x), getY() + (newLocation.y - oldLocation.y));
		
		if(Math.abs(newLocation.x - oldLocation.x) > 5 || Math.abs(newLocation.y - oldLocation.y) > 5)
		{
        		setLocation(point);
        
        		((Component) event.getSource()).getParent().repaint();
		}
	    }
	});
    }

    private static final long serialVersionUID = 4655550813193052386L;
}
