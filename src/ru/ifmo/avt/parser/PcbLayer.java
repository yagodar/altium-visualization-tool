package ru.ifmo.avt.parser;

public class PcbLayer {	
	public int getId() {
		return id;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean isTop() {
		return id == TOP_LAYER_ID;
	}
	
	public boolean isBottom() {
		return id == BOTTOM_LAYER_ID;
	}
	
	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	public float getDepth() {
		return depth;
	}
	
	public void setMaterialName(String materialName) {
		if(materialName != null) {
			this.materialName = materialName;
		}
	}
	
	public String getMaterialName() {
		return materialName;
	}
	
	protected PcbLayer(int id) {
		this(id, false, 0.0f, "unknown");
	}
	
	protected PcbLayer(int id, boolean isEnabled, float depth, String materialName) {
		this.id = id;
		this.isEnabled = isEnabled;
		this.depth = depth;
		
		if(materialName != null) {
			this.materialName = materialName;
		}
		else {
			this.materialName = "unknown";
		}
	}
	
	protected static final int TOP_LAYER_ID = -1;
	protected static final int BOTTOM_LAYER_ID = -2;
	
	private boolean isEnabled;
	private float depth;
	private String materialName;
	
	private final int id;
}
