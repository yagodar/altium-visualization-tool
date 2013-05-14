package ru.ifmo.avt.browser.GUI.components;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;

import ru.ifmo.avt.browser.EntryPoint;

public class BrowserFrequencySpinner extends JSpinner {

	private static final long serialVersionUID = 391572636395722701L;

	public BrowserFrequencySpinner() {
		setModel(new AbstractSpinnerModel() {
			private static final long serialVersionUID = 6977542434223894156L;
			private int frequency = 100;

			@Override
			public void setValue(Object arg0) {
				if (arg0 instanceof Integer) {
					frequency = (int) arg0;
					EntryPoint.frequency = frequency;
					revalidate();
					fireStateChanged();
				} else
					throw new IllegalArgumentException();
			}

			@Override
			public Object getValue() {
				return frequency;
			}

			@Override
			public Object getPreviousValue() {
				return Math.max(frequency - 1, 1);
			}

			@Override
			public Object getNextValue() {
				return frequency + 1;
			}
		});

		setValue(100);
	}
}
