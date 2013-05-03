package ru.ifmo.avt.browser.interfaces;

import java.awt.Component;

public interface Propertiable {
	public String getName();

	public Object getValue();

	public void setValue(Object o);

	public Component getViewComponent();
}
