package ru.ifmo.avt.browser.tempInstance;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import ru.ifmo.avt.browser.interfaces.Propertiable;

public class PropertiableObject implements Propertiable {

    public PropertiableObject(String name, Object value) {
	this.name = name;
	this.value = value;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public Object getValue() {
	return value;
    }

    @Override
    public void setValue(Object value) {
	this.value = value;
    }

    @Override
    public Component getViewComponent() {
	if (getValue() instanceof Boolean) {
	    JCheckBox checkBox = new JCheckBox("", (Boolean) getValue());
	    checkBox.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent event) {
		    JCheckBox checkBox = (JCheckBox) event.getSource();
		    value = checkBox.isSelected();
		}
	    });
	    return checkBox;
	} else
	    return null;
    }

    private String name;
    private Object value;
}
