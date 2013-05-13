package ru.ifmo.avt.browser.GUI.components;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;

import ru.ifmo.avt.browser.EntryPoint;

public class BrowserScaleSpinner extends JSpinner {
    private static final long serialVersionUID = 8054106135098525769L;

    public BrowserScaleSpinner() {
	setModel(new AbstractSpinnerModel() {
	    private static final long serialVersionUID = 4593295508447082308L;
	    private double scale = 100.0;

	    @Override
	    public void setValue(Object arg0) {
		if (arg0 instanceof Double) {
		    if ((double) arg0 != scale) {
			EntryPoint.relativScale = (double) arg0 / scale;
			EntryPoint.scale = (double) arg0 / 100;
			if (EntryPoint.browser != null)
			    EntryPoint.browser.getBrowserWorkPanel().rebuild();
		    }
		    scale = (double) arg0;
		    fireStateChanged();
		} else
		    throw new IllegalArgumentException();
	    }

	    @Override
	    public Object getValue() {
		return scale;
	    }

	    @Override
	    public Object getPreviousValue() {
		return Math.max(scale - 10.0, 10.0);
	    }

	    @Override
	    public Object getNextValue() {
		return scale + 10.0;
	    }
	});

	setValue(100.0);
    }
}
