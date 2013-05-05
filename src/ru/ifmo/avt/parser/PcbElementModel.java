package ru.ifmo.avt.parser;

import java.util.Collections;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;

public class PcbElementModel extends AbstractPcbObject {
	@Override
	public String toString() {
		return PcbElementModel.class.getSimpleName() + " [id:" + getId() + "]" + " [depth:" + getDepth() + "mil]" + " [designator:" + getDesignatorName() + "]" + " [libRef:" + getLibraryReference() + "]"  + " [descr:" + getDescription() + "]";
	}
	
	@Override
	public List<Browserable> getBrowserableObjects() {
		return Collections.emptyList();
	}
	
	@Override
	public String getDescription() {
		String value = "unknown";
		
		Propertiable property = getPropertyByMark(PcbObjectPropertyMark.DESCRIPTION.toString());
		if(property != null) {
			value = (String) property.getValue();
		}
		else {
			setDescription(value);
		}
		
		return value;
	}
	
	public void setDescription(String description) {
		setProperty(PcbObjectPropertyMark.DESCRIPTION, description);
	}
	
	@Override
	public double getDepth() {
		double depth = 0.0;
		
		Propertiable depthProperty = getPropertyByMark(PcbObjectPropertyMark.DEPTH.toString());
		if(depthProperty != null) {
			depth = (double) depthProperty.getValue();
		}
		
		return depth;
	}

	public String getPatternName() {
		String value = "unknown";
		
		Propertiable property = getPropertyByMark(PcbObjectPropertyMark.PATTERN.toString());
		if(property != null) {
			value = (String) property.getValue();
		}
		else {
			setPatternName(value);
		}
		
		return value;
	}

	public void setPatternName(String patternName) {
		setProperty(PcbObjectPropertyMark.PATTERN, patternName);
	}

	public String getDesignatorName() {
		String value = "unknown";
		
		Propertiable property = getPropertyByMark(PcbObjectPropertyMark.DESIGNATOR.toString());
		if(property != null) {
			value = (String) property.getValue();
		}
		else {
			setDesignatorName(value);
		}
		
		return value;
	}

	public void setDesignatorName(String designatorName) {
		setProperty(PcbObjectPropertyMark.DESIGNATOR, designatorName);
	}

	public String getLibraryReference() {
		String value = "unknown";
		
		Propertiable property = getPropertyByMark(PcbObjectPropertyMark.LIBRARY_REFERENCE.toString());
		if(property != null) {
			value = (String) property.getValue();
		}
		else {
			setLibraryReference(value);
		}
		
		return value;
	}

	public void setLibraryReference(String libraryReference) {
		setProperty(PcbObjectPropertyMark.LIBRARY_REFERENCE, libraryReference);
	}

	public String getFootprintDescription() {
		String value = "unknown";
		
		Propertiable property = getPropertyByMark(PcbObjectPropertyMark.FOOTPRINT_DESCRIPTION.toString());
		if(property != null) {
			value = (String) property.getValue();
		}
		else {
			setFootprintDescription(value);
		}
		
		return value;
	}

	public void setFootprintDescription(String footprintDescription) {
		setProperty(PcbObjectPropertyMark.FOOTPRINT_DESCRIPTION, footprintDescription);
	}
	
	protected PcbElementModel(int id) {
		super();
		
		this.id = id;
	}
	
	protected int getId() {
		return id;
	}
	
	private final int id;
}
