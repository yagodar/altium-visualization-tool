package ru.ifmo.avt.parser;

import java.util.Collections;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;
import ru.ifmo.avt.tca.IPcbElementModelForTca;

class PcbElementModel extends AbstractPcbObject implements IPcbElementModelForTca {
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
		
		return value + "\nx:[" + getLocation().getX() + "] y:[" + getLocation().getY() + "]\nw:[" + getDimension().width + "] h:[" +  + getDimension().height;
	}
	
	@Override
	public double getDepth() {
		double depth = 0.0;
		
		Propertiable depthProperty = getPropertyByMark(PcbObjectPropertyMark.DEPTH.toString());
		if(depthProperty != null) {
			depth = (double) depthProperty.getValue();
		}
		else {
			setDepth(depth);
		}
		
		return depth;
	}
	
	@Override
	public double getPower() {
		double power = 0.0;
		
		Propertiable powerProperty = getPropertyByMark(PcbObjectPropertyMark.POWER.toString());
		if(powerProperty != null) {
			power = (double) powerProperty.getValue();
		}
		else {
			setPower(power);
		}
		
		return power;
	}
	
	public void setDescription(String description) {
		setProperty(PcbObjectPropertyMark.DESCRIPTION, description);
	}
	
	public void setPower(double power) {
		setProperty(PcbObjectPropertyMark.POWER, power);
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
		setSrcLocX(0.0);
		setSrcLocY(0.0);
	}
	
	protected double getSrcLocX() {
		return srcLocX;
	}

	protected void setSrcLocX(double srcLocX) {
		this.srcLocX = srcLocX;
	}

	protected double getSrcLocY() {
		return srcLocY;
	}

	protected void setSrcLocY(double srcLocY) {
		this.srcLocY = srcLocY;
	}

	protected int getId() {
		return id;
	}
	
	private double srcLocX;
	private double srcLocY;
	
	private final int id;
	
	protected static final double DEFAULT_WIDTH = 100.0;
	protected static final double DEFAULT_HEIGHT = 100.0;
}
