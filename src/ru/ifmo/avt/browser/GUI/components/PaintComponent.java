package ru.ifmo.avt.browser.GUI.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class PaintComponent extends JComponent {
	private static final long serialVersionUID = 939473172725933313L;

	public PaintComponent(Browserable browserableObject) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				Point point = event.getPoint();

				for (int i = 0; i < points.length; i++) {
					double x = points[i].getX() - SIZE / 2;
					double y = points[i].getY() - SIZE / 2;
					Rectangle2D shape = new Rectangle2D.Double(x, y, SIZE, SIZE);
					if (shape.contains(point)) {
						current = i;
						return;
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				current = -1;
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				if (current == -1)
					return;
				points[current] = event.getPoint();
				repaint();
			}
		});

		current = -1;

		setShapeMaker(browserableObject);
	}

	public void setShapeMaker(Browserable browserableObject) {
		this.browserableObject = browserableObject;
		points = browserableObject.getPeak();

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (points == null)
			return;

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < points.length; i++) {
			double x = points[i].getX() - SIZE / 2;
			double y = points[i].getY() - SIZE / 2;
			g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
		}

		g2.draw(browserableObject.makeShape(points));
	}

	private Point2D[] points;
	private static int SIZE = 4;
	private int current;
	private Browserable browserableObject;
}
