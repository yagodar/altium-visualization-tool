package ru.ifmo.avt.browser.GUI.components;

import java.awt.Color;

import javax.swing.JPanel;

import ru.ifmo.avt.browser.GUI.paintComponents.BoardPaintComponent;
import ru.ifmo.avt.browser.GUI.paintComponents.PaintPanelLayoutManager;
import ru.ifmo.avt.browser.interfaces.Browserable;

public class BrowserPaintPanel extends JPanel {

    private static final long serialVersionUID = 939473172725933313L;

    public BrowserPaintPanel(Browserable browserable) {

	setLayout(new PaintPanelLayoutManager());

	setBackground(bgColor);

	if (browserable != null && browserable.isBoard()) {
	    BoardPaintComponent boardComponent = new BoardPaintComponent(browserable);
	    add(boardComponent);
	}
    }

    private static Color bgColor = Color.WHITE;
}
