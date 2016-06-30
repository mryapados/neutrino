package fr.cedricsevestre.dto.engine;

import java.io.Serializable;

import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

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
