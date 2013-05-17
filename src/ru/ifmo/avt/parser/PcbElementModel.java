package ru.ifmo.avt.parser;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.tca.IPcbElementModelForTca;
import ru.ifmo.avt.vca.IPcbElementModelForVca;

class PcbElementModel extends AbstractPcbObject implements IPcbElementModelForTca, IPcbElementModelForVca {
	@Override
	public String toString() {
		return PcbElementModel.class.getSimpleName() + " [id:" + getId() + "]" + " [x:" + getSrcLocation().getX() + "] [y:" + getSrcLocation().getY() + "] [w:" + getWidth() + "] [h:" + getHeight() + "] [d:" + getDepth() + "] [designtr:" + getDesignatorName() + "]" + " [libRef:" + getLibraryReference() + "]"  + " [descr:" + getSrcDescription() + "]";
	}
	
	@Override
	public List<Browserable> getBrowserableObjects() {
		return Collections.emptyList();
	}
	
	@Override
	public String getDescription() {
		return "<html>" + getSrcDescription() + "<br />x:[" + getSrcLocation().getX() + "] y:[" + getSrcLocation().getY() + "]<br />w:[" + getWidth() + "] h:[" + getHeight() + "] d:[" + getDepth() + "]" + ( getPermissibleLoad() < getVibroAcceleration() / 9.81 ? "<br />Превышена допустимая нагрузка" : "" ) + "</html>";
	}
	
	@Override
	public double getPower() {
		return (double) getProperty(PcbObjectPropertyMark.POWER);
	}
	
	@Override
	public Color getColor() {
		if(getPermissibleLoad() < getVibroAcceleration() / 9.81)
		    return Color.RED;
		
		return new Color(DEFAULT_COLOR.getRed(), DEFAULT_COLOR.getGreen(), DEFAULT_COLOR.getBlue());
	}

	@Override
	public boolean isBoard() {
	    return false;
	}
	
	@Override
	public double getPermissibleLoad() {
		return (double) getProperty(PcbObjectPropertyMark.PERMISSIBLE_LOAD);
	}
	
	@Override
	public void setVibroAcceleration(double value) {
		setProperty(PcbObjectPropertyMark.VIBRO_ACCELERAION, value);
	}
	
	public void setPower(double value) {
		setProperty(PcbObjectPropertyMark.POWER, value);
	}
	
	public double getVibroAcceleration() {
		return (double) getProperty(PcbObjectPropertyMark.VIBRO_ACCELERAION);
	}

	public void setPermissibleLoad(double value) {
		setProperty(PcbObjectPropertyMark.PERMISSIBLE_LOAD, value);
	}
	
	public String getPatternName() {
		return (String) getProperty(PcbObjectPropertyMark.PATTERN);
	}

	public void setPatternName(String patternName) {
		setProperty(PcbObjectPropertyMark.PATTERN, patternName);
	}

	public String getDesignatorName() {
		return (String) getProperty(PcbObjectPropertyMark.DESIGNATOR);
	}

	public void setDesignatorName(String designatorName) {
		setProperty(PcbObjectPropertyMark.DESIGNATOR, designatorName);
	}

	public String getLibraryReference() {
		return (String) getProperty(PcbObjectPropertyMark.LIBRARY_REFERENCE);
	}

	public void setLibraryReference(String libraryReference) {
		setProperty(PcbObjectPropertyMark.LIBRARY_REFERENCE, libraryReference);
	}

	public String getFootprintDescription() {
		return (String) getProperty(PcbObjectPropertyMark.FOOTPRINT_DESCRIPTION);
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
	
	protected void setSrcDescription(String description) {
		setProperty(PcbObjectPropertyMark.SRC_DESCRIPTION, description);
	}
	
	protected String getSrcDescription() {
		return (String) getProperty(PcbObjectPropertyMark.SRC_DESCRIPTION);
	}
	
	protected int getId() {
		return id;
	}
	
	private final int id;
	
	protected static final double DEFAULT_WIDTH = 100.0;
	protected static final double DEFAULT_HEIGHT = 100.0;
	
	private static final Color DEFAULT_COLOR = new Color(55, 55, 55);
}
