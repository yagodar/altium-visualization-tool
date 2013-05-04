package ru.ifmo.avt.browser.GUI.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import ru.ifmo.avt.browser.GUI.paintComponents.PaintComponent;
import ru.ifmo.avt.browser.interfaces.Browserable;

public class BrowserPaintComponent extends JComponent {
    private static final long serialVersionUID = 939473172725933313L;

    public BrowserPaintComponent(Browserable browserable) {
	this.browserable = browserable;
	selectComponent = null;
	add(browserable);

	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mousePressed(MouseEvent event) {
		if (event.getSource() instanceof BrowserPaintComponent)
		    return;

		if (selectComponent != null)
		    selectComponent.deselect();

		selectComponent = (PaintComponent) event.getSource();

		/*
		 * component.get for (int i = 0; i < points.length; i++) {
		 * double x = points[i].getX() - SIZE / 2; double y =
		 * points[i].getY() - SIZE / 2; Rectangle2D shape = new
		 * Rectangle2D.Double(x, y, SIZE, SIZE); if
		 * (shape.contains(point)) { current = i; currentShape = points;
		 * return; } } if (currentShape != null) return; }
		 */
	    }
	    /*
	     * @Override public void mouseReleased(MouseEvent event) { shapePeak
	     * = null; }
	     */
	});

	/*
	 * addMouseMotionListener(new MouseMotionAdapter() {
	 * 
	 * @Override public void mouseDragged(MouseEvent event) { if (shapePeak
	 * == null) return;
	 * 
	 * Point point = event.getPoint();
	 * 
	 * shapePeak. = event.getPoint(); repaint(); } });
	 */

	/*
	 * current = -1; currentShape = null;
	 */
    }

    public void add(Browserable browserable) {

	if (browserable.getBrowserableObjects() != null)
	    for (Browserable object : browserable.getBrowserableObjects())
		add(object);
	PaintComponent component = new PaintComponent(browserable);
	componentList.add(component);
	add(component);
    }

    @Override
    protected void paintComponent(Graphics g) {

	/*
	 * if (componentList.isEmpty()) return;
	 * 
	 * Graphics2D g2 = (Graphics2D) g;
	 * 
	 * for (PaintComponent component : componentList) { for (Shape shape :
	 * component.getShapesPeakList()) g2.fill(shape);
	 */

	super.paintComponent(g);

	// }
    }

    private List<PaintComponent> componentList = new LinkedList<PaintComponent>();

    private PaintComponent selectComponent;
    private Shape shapePeak;
    private static int SIZE = 4;
    private Browserable browserable;
}
