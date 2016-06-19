package fr.cedricsevestre.dto.back;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.MapTemplateService;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;

public class BlockDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer idMapTemplate;

	public BlockDto() {

	}

	public BlockDto(Integer id, String name, Integer idMapTemplate) {
		super();
		this.id = id;
		this.name = name;
		this.idMapTemplate = idMapTemplate;
	}

	public static BlockDto from(MapTemplate mapTemplate) {
		Template template = mapTemplate.getBlock();
		return new BlockDto(template.getId(), template.getName(), mapTemplate.getId());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdMapTemplate() {
		return idMapTemplate;
	}

	public void setIdMapTemplate(Integer idMapTemplate) {
		this.idMapTemplate = idMapTemplate;
	}

	


	
	
}
