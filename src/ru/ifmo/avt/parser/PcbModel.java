package ru.ifmo.avt.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;
import ru.ifmo.avt.tca.IPcbElementModelForTca;
import ru.ifmo.avt.tca.IPcbModelForTca;

public class PcbModel extends AbstractPcbObject implements IPcbModelForTca {
	@Override
	public String toString() {
		return PcbModel.class.getSimpleName() + " [name:" + getName() + "]" + " [width:" + getWidth() + "mil]" + " [height:" + getHeight() + "mil]" + " [depth:" + getDepth() + "mil]" + " [elementsCount:" + getAllElements().size() + "]";
	}
	
	@Override
	public double getDepth() {
		double depth = 0.0;

		Propertiable depthProperty = getPropertyByMark(PcbObjectPropertyMark.DEPTH.toString());
		if(depthProperty != null) {
			depth = (double) depthProperty.getValue();
		}
		else {
			for (PcbLayer layer : getAllLayers()) {
				if(layer.isEnabled()) {
					depth += layer.getDepth();
				}
			}

			setDepth(depth);
		}
		
		return depth;
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
	public String getDescription() {
		return "<html>" + getName() + "<br />x:[" + getLocation().getX() + "] y:[" + getLocation().getY() + "]<br />w:[" + getDimension().width + "] h:[" + getDimension().height + "] d:[" + getDepth() + "] </html>";
	}
	
	@Override
	public double getEnvThermalConduct() {
		double envThermalConduct = DEFAULT_ENV_TERMAL_CONDACT;
		
		Propertiable envThermalConductProperty = getPropertyByMark(PcbObjectPropertyMark.ENV_TERMAL_CONDUCT.toString());
		if(envThermalConductProperty != null) {
			envThermalConduct = (double) envThermalConductProperty.getValue();
		}
		else {
			setEnvThermalConduct(envThermalConduct);
		}
		
		return envThermalConduct;
	}
	
	@Override
	public double getEnvTemperature() {
		double envTemperature = DEFAULT_ENV_TEMPERATURE;
		
		Propertiable envTemperatureProperty = getPropertyByMark(PcbObjectPropertyMark.ENV_TEMPERATURE.toString());
		if(envTemperatureProperty != null) {
			envTemperature = (double) envTemperatureProperty.getValue();
		}
		else {
			setEnvTemperature(envTemperature);
		}
		
		return envTemperature;
	}
	
	@Override
	public Color getColor() {
	    return DEFAULT_COLOR;
	}

	@Override
	public boolean isBoard() {
	    return true;
	}
	
	public void setEnvThermalConduct(double envThermalConduct) {
		setProperty(PcbObjectPropertyMark.ENV_TERMAL_CONDUCT, envThermalConduct);
	}
	
	public void setEnvTemperature(double envTemperature) {
		setProperty(PcbObjectPropertyMark.ENV_TEMPERATURE, envTemperature);
	}
	
	public String getName() {
		String name = "unknown";
		
		Propertiable depthProperty = getPropertyByMark(PcbObjectPropertyMark.NAME.toString());
		if(depthProperty != null) {
			name = (String) depthProperty.getValue();
		}
		else {
			setName(name);
		}
		
		return name;
	}
	
	public void setName(String name) {
		setProperty(PcbObjectPropertyMark.NAME, name);
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
	
	protected static final double DEFAULT_LOC_X = 100;
	protected static final double DEFAULT_LOC_Y = 100;
	
	private static final double DEFAULT_ENV_TERMAL_CONDACT = 0.026;
	private static final double DEFAULT_ENV_TEMPERATURE = 20;
	private static final Color DEFAULT_COLOR = new Color(5, 95, 14);
}
