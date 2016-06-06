package fr.cedricsevestre.dto.back;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import fr.cedricsevestre.entity.back.Base;
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.MapTemplateService;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;

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
