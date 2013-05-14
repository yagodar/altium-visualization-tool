package ru.ifmo.avt.parser;

import java.awt.Component;
import java.awt.Point;
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
		if(o instanceof String && value instanceof Double) {
			value = Double.parseDouble((String)o);
		}
		else {
			value = o;
		}
		
		if(PcbObjectPropertyMark.WIDTH.getName().equals(getName())) {
			double scaleX = 1.0;
			
			Point srcLoc = owner.getSrcLocation();
			Point loc = owner.getLocation();
			
			if(srcLoc != null && loc != null) {
				scaleX = loc.getX() / srcLoc.getX();
			}
			
			owner.getRightBottomPeakPoint().setLocation((double) value * scaleX, owner.getRightBottomPeakPoint().getY());
		}
		else if(PcbObjectPropertyMark.HEIGHT.getName().equals(getName())) {
			double scaleY = 1.0;
			
			Point srcLoc = owner.getSrcLocation();
			Point loc = owner.getLocation();
			
			if(srcLoc != null && loc != null) {
				scaleY = loc.getY() / srcLoc.getY();
			}
			
			owner.getRightBottomPeakPoint().setLocation(owner.getRightBottomPeakPoint().getX(), (double) value * scaleY);
		}
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

	protected PcbObjectProperty(AbstractPcbObject owner, String name, Object value) {
		this.name = name;
		
		this.owner = owner;
		setValue(value);
	}
	
	private AbstractPcbObject owner;
	private Object value;
	
	private final String name;	 
}
