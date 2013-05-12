package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class PaintPanelLayoutManager implements LayoutManager {

    @Override
    public void addLayoutComponent(String arg0, Component arg1) {
    }

    @Override
    public void layoutContainer(Container parent) {
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
	setSizes(parent);
	return dimension;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
	setSizes(parent);
	return dimension;
    }

    @Override
    public void removeLayoutComponent(Component arg0) {
    }

    private void setSizes(Container parent) {
	preferredHeight = 0;
	preferredWidth = 0;

	for (Component component : parent.getComponents()) {
	    double width = component.getLocation().getX() + component.getPreferredSize().getWidth() + SPACE_SIZE;
	    double height = component.getLocation().getY() + component.getPreferredSize().getHeight() + SPACE_SIZE;

	    if (height > preferredHeight)
		preferredHeight = height;

	    if (width > preferredWidth)
		preferredWidth = width;
	}

	dimension = new Dimension((int) preferredWidth +  50, (int) preferredHeight + 50);
    }

    private int SPACE_SIZE = 0;
    private Dimension dimension = new Dimension();
    private double preferredWidth = 0;
    private double preferredHeight = 0;
}
