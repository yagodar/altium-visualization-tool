package ru.ifmo.avt.browser.GUI.components;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

import ru.ifmo.avt.browser.EntryPoint;
import ru.ifmo.avt.browser.GUI.paintComponents.BoardPaintComponent;
import ru.ifmo.avt.browser.GUI.paintComponents.PaintPanelLayoutManager;
import ru.ifmo.avt.browser.interfaces.Browserable;

public class BrowserPaintPanel extends JPanel {

    private static final long serialVersionUID = 939473172725933313L;

    public BrowserPaintPanel(Browserable browserable) {

	setLayout(new PaintPanelLayoutManager());

	setBackground(bgColor);

	if (browserable != null && browserable.isBoard()) {
	    scale(browserable);
	    BoardPaintComponent boardComponent = new BoardPaintComponent(browserable);
	    add(boardComponent);
	}
    }

    public static void scale(Browserable browserable) {
	browserable.getLocation().setLocation(browserable.getLocation().getX() * EntryPoint.relativScale, browserable.getLocation().getY() * EntryPoint.relativScale);
	for (Point p : browserable.getPeak())
	    p.setLocation(p.getX() * EntryPoint.relativScale, p.getY() * EntryPoint.relativScale);
    }

    private static Color bgColor = Color.WHITE;
}
