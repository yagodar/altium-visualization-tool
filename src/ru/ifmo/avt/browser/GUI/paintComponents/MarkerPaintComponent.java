package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class MarkerPaintComponent extends DraggedPaintComponent {

    private static final long serialVersionUID = -5523399180337116086L;

    public MarkerPaintComponent(ElementPaintComponent elementPaintComponent, final Point point) {
	this.elementPaintComponent = elementPaintComponent;
	this.point = point;

	Dimension dimension = new Dimension(SIZE, SIZE);

	relocation();
	setSize(dimension);
	setPreferredSize(dimension);
	setToolTipText(point.toString());
	setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	Rectangle2D shape = new Rectangle2D.Double();
	shape.setFrameFromDiagonal(new Point(0, 0), new Point(SIZE, SIZE));
	g2.setColor(bgColor);
	g2.fill(shape);
	shape.setFrameFromDiagonal(new Point(0, 0), new Point(SIZE - 1, SIZE - 1));
	g2.setColor(boardColor);
	g2.draw(shape);
	super.paintComponent(g);
    }

    public void relocation() {
        super.setLocation(new Point((int) (point.getX() + elementPaintComponent.getLocation().getX()) - SIZE / 2, (int) (point.getY() + elementPaintComponent.getLocation().getY()) - SIZE / 2));
    }
    
    @Override
    public void setLocation(Point p) {
	super.setLocation(p);
	point.setLocation((int)( p.getX() - elementPaintComponent.getLocation().getX()) + SIZE / 2, (int) (p.getY() - elementPaintComponent.getLocation().getY()) + SIZE / 2);
	setToolTipText(p.toString());
    }

    private static Color boardColor = Color.GRAY;
    private static Color bgColor = Color.BLUE;
    private static int SIZE = 6;
    private ElementPaintComponent elementPaintComponent = null;
    private Point point = null;
}
