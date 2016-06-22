package fr.cedricsevestre.dto.engine;

import java.io.Serializable;

public class MapTemplateSimpleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String modelName;
	private String blockName;
	private String positionName;
	private Integer ordered;

	public MapTemplateSimpleDto() {

	}

	public MapTemplateSimpleDto(String modelName, String blockName, String positionName, Integer ordered) {
		super();
		this.modelName = modelName;
		this.blockName = blockName;
		this.positionName = positionName;
		this.ordered = ordered;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	@Override
	public String toString() {
		return "MapTemplateSimpleDto [modelName=" + modelName + ", blockName=" + blockName + ", positionName="
				+ positionName + ", ordered=" + ordered + "]";
	}

	


	
	
}
