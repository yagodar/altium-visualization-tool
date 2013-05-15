package ru.ifmo.avt.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.tca.IPcbElementModelForTca;
import ru.ifmo.avt.tca.IPcbModelForTca;
import ru.ifmo.avt.vca.IPcbElementModelForVca;
import ru.ifmo.avt.vca.IPcbModelForVca;

public class PcbModel extends AbstractPcbObject implements IPcbModelForTca, IPcbModelForVca {
	@Override
	public String toString() {
		return PcbModel.class.getSimpleName() + " [name:" + getName() + "]" + " [width:" + getWidth() + "mil]" + " [height:" + getHeight() + "mil]" + " [depth:" + getDepth() + "mil]" + " [elementsCount:" + getAllElements().size() + "]";
	}
	
	@Override
	public List<Browserable> getBrowserableObjects() {
		List<Browserable> browserableObjects = new ArrayList<Browserable>();
		browserableObjects.addAll(getAllElements());
		return browserableObjects;
	}
	
	@Override
	public List<IPcbElementModelForTca> getElementsForTca() {
		List<IPcbElementModelForTca> elementsForTca = new ArrayList<IPcbElementModelForTca>();
		elementsForTca.addAll(getAllElements());
		return elementsForTca;
	}
	
	@Override
	public List<IPcbElementModelForVca> getElementsForVca() {
		List<IPcbElementModelForVca> elementsForVca = new ArrayList<IPcbElementModelForVca>();
		elementsForVca.addAll(getAllElements());
		return elementsForVca;
	}
	
	@Override
	public String getDescription() {
		return "<html>" + getName() + "<br />x:[" + getSrcLocation().getX() + "] y:[" + getSrcLocation().getY() + "]<br />w:[" + getWidth() + "] h:[" + getHeight() + "] d:[" + getDepth() + "] </html>";
	}
	
	@Override
	public double getEnvThermalConduct() {
		return (double) getProperty(PcbObjectPropertyMark.ENV_TERMAL_CONDUCT);
	}
	
	@Override
	public double getEnvTemperature() {
		return (double) getProperty(PcbObjectPropertyMark.ENV_TEMPERATURE);
	}
	
	@Override
	public Color getColor() {
	    return DEFAULT_COLOR;
	}

	@Override
	public boolean isBoard() {
	    return true;
	}
	
	@Override
	public double getModuleElasticity() {
		return (double) getProperty(PcbObjectPropertyMark.MODULE_ELASTICITY);
	}
	
	@Override
	public double getDensity() {
		return (double) getProperty(PcbObjectPropertyMark.DENSITY);
	}
	
	@Override
	public double getPuassonsCoefficient() {
		return (double) getProperty(PcbObjectPropertyMark.PUASSONS_COEF);
	}
	
	@Override
	public double getWeightAllElements() {
		return (double) getProperty(PcbObjectPropertyMark.WEIGHT_ALL_ELEMENTS);
	}
	
	@Override
	public void setFrequency(double value) {
		setProperty(PcbObjectPropertyMark.FREQUENCY, value);
	}
	
	@Override
	public void setDisplacement(double value) {
		setProperty(PcbObjectPropertyMark.DISPLACEMENT, value);
	}
	
	@Override
	public void setOwnFrequency(double value) {
		setProperty(PcbObjectPropertyMark.OWN_FREQUENCY, value);
	}
	
	@Override
	public void setMaxDeflection(double value) {
		setProperty(PcbObjectPropertyMark.MAX_DEFLECTION, value);
	}
	
	@Override
	public void setDeflection(double value) {
		setProperty(PcbObjectPropertyMark.DEFLECTION, value);
	}
	
	public void setEnvThermalConduct(double value) {
		setProperty(PcbObjectPropertyMark.ENV_TERMAL_CONDUCT, value);
	}
	
	public void setEnvTemperature(double value) {
		setProperty(PcbObjectPropertyMark.ENV_TEMPERATURE, value);
	}
	
	public void setModuleElasticity(double value) {
		setProperty(PcbObjectPropertyMark.MODULE_ELASTICITY, value);
	}
	
	public void setDensity(double value) {
		setProperty(PcbObjectPropertyMark.DENSITY, value);
	}
	
	public void setPuassonsCoefficient(double value) {
		setProperty(PcbObjectPropertyMark.PUASSONS_COEF, value);
	}
	
	public void setWeightAllElements(double value) {
		setProperty(PcbObjectPropertyMark.WEIGHT_ALL_ELEMENTS, value);
	}
	
	public double getFrequency() {
		return (double) getProperty(PcbObjectPropertyMark.FREQUENCY);
	}
	
	public double getDisplacement() {
		return (double) getProperty(PcbObjectPropertyMark.DISPLACEMENT);
	}
	
	public double getOwnFrequency() {
		return (double) getProperty(PcbObjectPropertyMark.OWN_FREQUENCY);
	}
	
	public double getMaxDeflection() {
		return (double) getProperty(PcbObjectPropertyMark.MAX_DEFLECTION);
	}
	
	public double getDeflection() {
		return (double) getProperty(PcbObjectPropertyMark.DEFLECTION);
	}
	
	public String getName() {
		return (String) getProperty(PcbObjectPropertyMark.NAME);
	}
	
	public void setName(String value) {
		setProperty(PcbObjectPropertyMark.NAME, value);
	}
	
	protected PcbModel() {
		super();
		
		layersByMark = new HashMap<String, PcbLayer>();
		elementsById = new HashMap<Integer, PcbElementModel>();
	}
	
	protected void addLayer(String layerMark, PcbLayer layer) {
		layersByMark.put(layerMark, layer);
	}
	
	protected PcbLayer getTopLayer() {
		return layersByMark.get(PcbLayer.TOP_LAYER_MARK);
	}
	
	protected PcbLayer getLayer(String layerMark) {
		return layersByMark.get(layerMark);
	}
	
	protected PcbLayer getBottomLayer() {
		return layersByMark.get(PcbLayer.BOTTOM_LAYER_MARK);
	}
	
	protected Collection<PcbLayer> getAllLayers() {
		return layersByMark.values();
	}
	
	protected void addElement(int elementId, PcbElementModel element) {
		elementsById.put(elementId, element);
	}
	
	protected PcbElementModel getElement(int elementId) {
		return elementsById.get(elementId);
	}
	
	protected Collection<PcbElementModel> getAllElements() {
		return elementsById.values();
	}
	
	private HashMap<String, PcbLayer> layersByMark;
	private HashMap<Integer, PcbElementModel> elementsById;
	
	protected static final double DEFAULT_VIEW_LOC_X = 100.0;
	protected static final double DEFAULT_VIEW_LOC_Y = 100.0;
	
	private static final Color DEFAULT_COLOR = new Color(5, 95, 14);
}
