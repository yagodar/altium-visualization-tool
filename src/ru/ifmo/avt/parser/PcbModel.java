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
		return layersById.get(PcbLayer.TOP_LAYER_ID);
	}
	
	public PcbLayer getLayer(int layerId) {
		return layersById.get(layerId);
	}
	
	public PcbLayer getBottomLayer() {
		return layersById.get(PcbLayer.BOTTOM_LAYER_ID);
	}
	
	public Collection<PcbLayer> getAllLayers() {
		return layersById.values();
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
	
	public ArrayList<HashMap<String, String>> getAdditionalProps(String propsType) {
		return additionalProps.get(propsType);
	}
	
	public Collection<ArrayList<HashMap<String, String>>> getAllAdditionalProps() {
		return additionalProps.values();
	}
	
	protected void addAdditionalProps(String propsType, HashMap<String, String> props) {
		if(propsType != null && !propsType.isEmpty() && props != null) {
			if(additionalProps.get(propsType) == null) {
				additionalProps.put(propsType, new ArrayList<HashMap<String, String>>());
			}
			
			additionalProps.get(propsType).add(props);
		}
	}
	
	protected PcbModel() {
		super();
		
		name = "unknown";
		
		layersById = new HashMap<Integer, PcbLayer>();
		depth = -1.0f;
		
		elementsById = new HashMap<Integer, PcbElementModel>();
		
		additionalProps = new HashMap<String, ArrayList<HashMap<String, String>>>();
	}
	
	protected void addLayer(int layerId, PcbLayer layer) {
		layersById.put(layerId, layer);
	}
	
	protected void addElement(int elementId, PcbElementModel element) {
		elementsById.put(elementId, element);
	}
	
	private String name;
	
	private HashMap<Integer, PcbLayer> layersById;
	private float depth;
	
	private HashMap<Integer, PcbElementModel> elementsById;
	
	private final HashMap<String, ArrayList<HashMap<String, String>>> additionalProps;
}
