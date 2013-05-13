package ru.ifmo.avt.browser.GUI.components;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.ifmo.avt.browser.helper.GBC;
import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;

public class PropertyEditorPanel extends JPanel {

    private static final long serialVersionUID = 5787620610471086039L;

    public PropertyEditorPanel(Browserable browserableObject) {
	setLayout(new GridBagLayout());

	if (browserableObject != null) {
	    List<Propertiable> properties = browserableObject.getProperties();

	    for (Propertiable property : properties) {
		String name = property.getName();
		Component component = property.getViewComponent();
		if (component == null)
		    component = new TextFieldViewComponent(property);

		/*
		 * component.addFocusListener(new FocusAdapter() {
		 * 
		 * @Override public void focusLost(FocusEvent event) {
		 * event.getComponent().getParent(.getParent()).repaint(); } });
		 */
		int index = properties.indexOf(property);

		add(new JLabel(name), new GBC(0, index).setAnchor(GBC.FIRST_LINE_START).setFill(GBC.BOTH).setInsets(0, 5, 0, 5));
		add(component, new GBC(1, index).setAnchor(GBC.FIRST_LINE_START).setFill(GBC.BOTH).setWeight(100, 0));
	    }

	    add(new JLabel(""), new GBC(0, properties.size()).setWeight(0, 100));
	} else
	    add(new JLabel(""), new GBC(0, 0).setWeight(0, 100));
    }
}
