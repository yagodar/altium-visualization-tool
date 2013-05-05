package ru.ifmo.avt.parser;

public class PcbLayer {	
	public String getMark() {
		return mark;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean isTop() {
		return mark == TOP_LAYER_MARK;
	}
	
	public boolean isBottom() {
		return mark == BOTTOM_LAYER_MARK;
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
	
	@Override
	public String toString() {
		return PcbLayer.class.getSimpleName() + " [mark:" + getMark() + "]" + " [isEnabled:" + isEnabled() + "]" + " [depth:" + getDepth() + "mil]" + " [material:" + getMaterialName() + "]";
	}
	
	protected PcbLayer(String mark) {
		this(mark, false, 0.0f, "unknown");
	}
	
	protected PcbLayer(String mark, boolean isEnabled, float depth, String materialName) {
		this.mark = mark;
		this.isEnabled = isEnabled;
		this.depth = depth;
		
		if(materialName != null) {
			this.materialName = materialName;
		}
		else {
			this.materialName = "unknown";
		}
	}
	
	protected static final String TOP_LAYER_MARK = "TOP";
	protected static final String BOTTOM_LAYER_MARK = "BOTTOM";
	
	private boolean isEnabled;
	private float depth;
	private String materialName;
	
	private final String mark;
}
