package ru.ifmo.avt.browser.GUI.components;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;

import ru.ifmo.avt.browser.EntryPoint;

public class BrowserAccelerationSpinner extends JSpinner {
	private static final long serialVersionUID = 4683032039507270018L;

	public BrowserAccelerationSpinner() {
		setModel(new AbstractSpinnerModel() {
			private static final long serialVersionUID = 5963665589579324264L;
			private double acceleration = 20.0;

			@Override
			public void setValue(Object arg0) {
				if (arg0 instanceof Double) {
					acceleration = (double) arg0;
					EntryPoint.acceleration = acceleration;
					revalidate();
					fireStateChanged();
				} else
					throw new IllegalArgumentException();
			}

			@Override
			public Object getValue() {
				return acceleration;
			}

			@Override
			public Object getPreviousValue() {
				return Math.max(acceleration - 0.1, 0.1);
			}

			@Override
			public Object getNextValue() {
				return acceleration + 0.1;
			}
		});

		setValue(20.0);
	}
}
