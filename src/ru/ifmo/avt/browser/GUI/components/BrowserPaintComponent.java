package ru.ifmo.avt.browser.GUI.components;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import ru.ifmo.avt.browser.GUI.paintComponents.PaintComponent;
import ru.ifmo.avt.browser.interfaces.Browserable;

public class BrowserPaintComponent extends JPanel {
    
    private static final long serialVersionUID = 939473172725933313L;

    public BrowserPaintComponent(Browserable browserable) {

	super();
/*	JButton button = new JButton("OK");
	button.setSize(400, 400);
	add(button);
	JButton button2 = new JButton("OK");
	button2.setLocation(new Point(400, 400));
	button2.setSize(400, 400);
	add(button2);*/
	this.browserable = browserable;
	setLayout(null);
	//TextField text = new TextField("ojfhgijuangoadnfogpnarjopngjarngjoandfogpnadfongajosdfgnjioadfnjuip");
	//text.setSize(50, 500);
	//text.setLocation(10, 10);
	//add(text);
	//setPreferredSize(new Dimension(5000, 5000));
	add(browserable);

	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mousePressed(MouseEvent event) {

		if (event.getSource() instanceof BrowserPaintComponent) {
		    //selectComponent = null;
		    //current = -1;
		    return;
		}

		selectComponent = (PaintComponent) event.getSource();
		
		Point clickedPoint = new Point((int)(event.getPoint().getX() + selectComponent.getLocation().getX()), (int)(event.getPoint().getY() + selectComponent.getLocation().getY()));

		Point[] points = selectComponent.getPeak();

		for (int i = 0; i < points.length; i++) {
		    Point location = new Point((int)(points[i].getX() + selectComponent.getLocation().getX()) - SIZE/2, (int)(points[i].getY() + selectComponent.getLocation().getY()) - SIZE/2);
		    Rectangle2D shape = new Rectangle2D.Double();
		    shape.setFrame(location, new Dimension(SIZE, SIZE));
		    if (shape.contains(clickedPoint)) {
			current = i;
			return;
		    }
		}

		repaint();
	    }

	    @Override
	    public void mouseReleased(MouseEvent event) {
		//selectComponent = null;
		//current = -1;
	    }

	});

	addMouseMotionListener(new MouseMotionAdapter() {

	    @Override
	    public void mouseDragged(MouseEvent event) {

		if(selectComponent == null || current < 0)
		    return;
		
		selectComponent.getPeak()[current].setLocation(event.getPoint().getX(), event.getPoint().getY());
		repaint();
	    }
	});
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
	  
	  super.paintComponent(g);
	  
	  if (componentList.isEmpty()) 
	      return;
	  
	  Graphics2D g2 = (Graphics2D) g;
	  
	  if(selectComponent != null)
	  {
		Point[] points = selectComponent.getPeak();

		for (int i = 0; i < points.length; i++) {
		    Point location = new Point((int)(points[i].getX() + selectComponent.getLocation().getX()) - SIZE/2, (int)(points[i].getY() + selectComponent.getLocation().getY()) - SIZE/2);
		    Rectangle2D shape = new Rectangle2D.Double();
		    shape.setFrame(location, new Dimension(SIZE, SIZE));
		    g2.fill(shape);
		}
	  }
    }

    private List<PaintComponent> componentList = new LinkedList<PaintComponent>();

    private int current = -1;
    private PaintComponent selectComponent = null;
    // private List<PeakContainer> shapesPeakList = new
    // LinkedList<PeakContainer>();
    private static int SIZE = 30;
    private Browserable browserable;
}
