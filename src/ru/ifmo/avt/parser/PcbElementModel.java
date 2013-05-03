package ru.ifmo.avt.parser;

public class PcbElementModel extends AbstractPcbObject {
	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public String getSrcDesignator() {
		return srcDesignator;
	}

	public void setSrcDesignator(String srcDesignator) {
		this.srcDesignator = srcDesignator;
	}

	public String getSrcLibRef() {
		return srcLibRef;
	}

	public void setSrcLibRef(String srcLibRef) {
		this.srcLibRef = srcLibRef;
	}

	public String getSrcDescr() {
		return srcDescr;
	}

	public void setSrcDescr(String srcDescr) {
		this.srcDescr = srcDescr;
	}

	public String getFootprintDescr() {
		return footprintDescr;
	}

	public void setFootprintDescr(String footprintDescr) {
		this.footprintDescr = footprintDescr;
	}

	public int getId() {
		return id;
	}
	
	protected PcbElementModel(int id) {
		this(id, -1.0f);
	}

	protected PcbElementModel(int id, float depth) {
		this(id, depth, "unknown", "unknown", "unknown", "unknown", "unknown");
	}
	
	protected PcbElementModel(int id, String patternName, String srcDesignator, String srcLibRef, String srcDescr, String footprintDescr) {
		this(id, -1.0f, patternName, srcDesignator, srcLibRef, srcDescr, footprintDescr);
	}
	
	protected PcbElementModel(int id, float depth, String patternName, String srcDesignator, String srcLibRef, String srcDescr, String footprintDescr) {
		this.id = id;
		this.depth = depth;
		
		if(patternName != null && !patternName.isEmpty()) {
			this.patternName = patternName;
		}
		else {
			this.patternName = "unknown";
		}
		
		if(srcDesignator != null && !srcDesignator.isEmpty()) {
			this.srcDesignator = srcDesignator;
		}
		else {
			this.srcDesignator = "unknown";
		}
		
		if(srcLibRef != null && !srcLibRef.isEmpty()) {
			this.srcLibRef = srcLibRef;
		}
		else {
			this.srcLibRef = "unknown";
		}
		
		if(srcDescr != null && !srcDescr.isEmpty()) {
			this.srcDescr = srcDescr;
		}
		else {
			this.srcDescr = "unknown";
		}
		
		if(footprintDescr != null && !footprintDescr.isEmpty()) {
			this.footprintDescr = footprintDescr;
		}
		else {
			this.footprintDescr = "unknown";
		}
	}
	
	private float depth;
	private String patternName;
	private String srcDesignator;
	private String srcLibRef;
	private String srcDescr;
	private String footprintDescr;
	private final int id;
}
