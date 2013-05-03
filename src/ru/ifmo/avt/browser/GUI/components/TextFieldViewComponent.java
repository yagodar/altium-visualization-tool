package ru.ifmo.avt.browser.GUI.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import ru.ifmo.avt.browser.interfaces.Propertiable;

public class TextFieldViewComponent extends JTextField {

    private static final long serialVersionUID = -1128546906819296840L;

    public TextFieldViewComponent(Propertiable property) {
	super(property.getValue().toString());

	this.property = property;

	addFocusListener(new FocusListener() {

	    @Override
	    public void focusLost(FocusEvent event) {
		JTextField textField = (JTextField) event.getSource();
		if (!oldValue.equals(textField.getText()))
		    TextFieldViewComponent.this.property.setValue(textField.getText());
	    }

	    @Override
	    public void focusGained(FocusEvent event) {
		JTextField textField = (JTextField) event.getSource();
		oldValue = textField.getText();
	    }

	    private String oldValue;
	});
    }

    private Propertiable property;
}
