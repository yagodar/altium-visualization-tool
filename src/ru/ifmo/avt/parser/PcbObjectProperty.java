package ru.ifmo.avt.parser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import ru.ifmo.avt.browser.interfaces.Propertiable;

class PcbObjectProperty implements Propertiable {
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object o) {
		this.value = o;
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
		} 
		else {
			return null;
		}
	}

	protected PcbObjectProperty(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	private Object value;
	
	private final String name;	 
}
