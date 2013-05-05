package ru.ifmo.avt.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PcbModel extends AbstractPcbObject {
	public void  setName(String name) {
		if(name != null) {
			this.name = name;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public PcbLayer getTopLayer() {
		return layersByMark.get(PcbLayer.TOP_LAYER_MARK);
	}
	
	public PcbLayer getLayer(String layerMark) {
		return layersByMark.get(layerMark);
	}
	
	public PcbLayer getBottomLayer() {
		return layersByMark.get(PcbLayer.BOTTOM_LAYER_MARK);
	}
	
	public Collection<PcbLayer> getAllLayers() {
		return layersByMark.values();
	}
	
	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	public float getDepth() {
		if(depth == -1.0f) {
			depth = 0.0f;
			
			for (PcbLayer layer : getAllLayers()) {
				if(layer.isEnabled()) {
					depth += layer.getDepth();
				}
			}
		}
		
		return depth;
	}
	
	public PcbElementModel getElement(int elementId) {
		return elementsById.get(elementId);
	}
	
	public Collection<PcbElementModel> getAllElements() {
		return elementsById.values();
	}
	
	public ArrayList<HashMap<String, String>> getOverAllProps(String propsType) {
		return overAllProps.get(propsType);
	}
	
	public Collection<ArrayList<HashMap<String, String>>> getAllOverAllProps() {
		return overAllProps.values();
	}
	
	@Override
	public String toString() {
		return PcbModel.class.getSimpleName() + " [name:" + getName() + "]" + " [absWidth:" + getAbsWidth() + "mil]" + " [absHeight:" + getAbsHeight() + "mil]" + " [depth:" + getDepth() + "mil]" + " [elementsCount:" + getAllElements().size() + "]";
	}
	
	protected void addAdditionalProps(String propsType, HashMap<String, String> props) {
		if(propsType != null && !propsType.isEmpty() && props != null) {
			if(overAllProps.get(propsType) == null) {
				overAllProps.put(propsType, new ArrayList<HashMap<String, String>>());
			}
			
			overAllProps.get(propsType).add(props);
		}
	}
	
	protected PcbModel() {
		super();
		
		name = "unknown";
		
		layersByMark = new HashMap<String, PcbLayer>();
		depth = -1.0f;
		
		elementsById = new HashMap<Integer, PcbElementModel>();
		
		overAllProps = new HashMap<String, ArrayList<HashMap<String, String>>>();
	}
	
	protected void addLayer(String layerMark, PcbLayer layer) {
		layersByMark.put(layerMark, layer);
	}
	
	protected void addElement(int elementId, PcbElementModel element) {
		elementsById.put(elementId, element);
	}
	
	private String name;
	
	private HashMap<String, PcbLayer> layersByMark;
	private float depth;
	
	private HashMap<Integer, PcbElementModel> elementsById;
	
	private final HashMap<String, ArrayList<HashMap<String, String>>> overAllProps;
}
