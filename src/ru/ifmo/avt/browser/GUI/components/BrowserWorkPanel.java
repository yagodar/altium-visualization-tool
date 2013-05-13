package ru.ifmo.avt.browser.GUI.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class BrowserWorkPanel extends JPanel {

    private static final long serialVersionUID = -7865115093245242000L;

    public BrowserWorkPanel() {
	setLayout(new BorderLayout());
    }

    public void setBrowserableObject(Browserable browserableObject) {
	this.browserable = browserableObject;
	removeAll();
	add(new JScrollPane(new PropertyEditorPanel(browserableObject), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.WEST);
	add(new JScrollPane(new BrowserPaintPanel(browserableObject), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
	revalidate();
    }

    public void rescale() {
	setBrowserableObject(browserable);
    }

    private Browserable browserable;
}
