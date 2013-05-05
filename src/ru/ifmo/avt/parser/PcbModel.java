package ru.ifmo.avt.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.browser.interfaces.Propertiable;

public class PcbModel extends AbstractPcbObject {
	@Override
	public String toString() {
		return PcbModel.class.getSimpleName() + " [name:" + getName() + "]" + " [width:" + getWidth() + "mil]" + " [height:" + getHeight() + "mil]" + " [depth:" + getDepth() + "mil]" + " [elementsCount:" + getAllElements().size() + "]";
	}
	
	@Override
	public double getDepth() {
		double depth = 0.0f;

		Propertiable depthProperty = getPropertyByMark(PcbObjectPropertyMark.DEPTH.toString());
		if(depthProperty != null) {
			depth = (double) depthProperty.getValue();
		}
		else {
			depth = 0.0f;

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
	public String getDescription() {
		return getName();
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
}
