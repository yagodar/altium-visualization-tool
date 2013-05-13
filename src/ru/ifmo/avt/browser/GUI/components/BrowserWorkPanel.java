package ru.ifmo.avt.browser.GUI.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

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
	setPropertyEditorPanel(browserableObject);
	setBrowserPaintPanel(browserableObject);
	revalidate();
    }

    public void rebuild() {
	setBrowserableObject(browserable);
    }

    public void setPropertyEditorPanel(Browserable browserableObject) {
	if (propertyEditorPanel != null)
	    remove(propertyEditorPanel);
	propertyEditorPanel = new JScrollPane(new PropertyEditorPanel(browserableObject), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	add(propertyEditorPanel, BorderLayout.WEST);
	revalidate();
    }

    public void setBrowserPaintPanel(Browserable browserableObject) {
	if (browserPaintPanel != null)
	    remove(browserPaintPanel);
	browserPaintPanel = new JScrollPane(new BrowserPaintPanel(browserableObject), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	add(browserPaintPanel, BorderLayout.CENTER);
	revalidate();
    }
    
    public JScrollPane getBrowserPaintPanel()
    {
	return browserPaintPanel;
    }

    private JScrollPane propertyEditorPanel;
    private JScrollPane browserPaintPanel;
    private Browserable browserable;
}
