package fr.cedricsevestre.dto.engine;

import java.io.Serializable;

import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

public class MapTemplateDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// On ne r�cup�re pas la position parcequ'on veut des �l�ments 
	// provenant de la position qu'on connait d�j� et parceque �a 
	// fait une boucle infinie car chaque position � une liste de 
	// mapTemplates et que chaque mapTemplate � une position
	
	private Integer id;
	private TranslationDto model;
	private TemplateDto block;
	private Integer ordered;

	public MapTemplateDto() {

	}

	public MapTemplateDto(Integer id, TranslationDto model, TemplateDto block, Integer ordered) {
		super();
		this.id = id;
		this.model = model;
		this.block = block;
		this.ordered = ordered;
	}

	public static MapTemplateDto from(MapTemplate mapTemplate) {
		return new MapTemplateDto(mapTemplate.getId(), TranslationDto.from(mapTemplate.getModel()), TemplateDto.from(mapTemplate.getBlock()), mapTemplate.getOrdered());
	}
	
//	// Si on veut convertir un objet MapTemplateDto, il faut fournir sa position
//	public static MapTemplate to(MapTemplateDto mapTemplateDto, PositionDto positionDto) {
//		return new MapTemplate(mapTemplateDto.getId(), TemplateDto.to(mapTemplateDto.getModel()), TemplateDto.to(mapTemplateDto.getBlock()), PositionDto.to(positionDto), mapTemplateDto.getOrdered());
//	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TranslationDto getModel() {
		return model;
	}

	public void setModel(TranslationDto model) {
		this.model = model;
	}

	public TemplateDto getBlock() {
		return block;
	}

	public void setBlock(TemplateDto block) {
		this.block = block;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	
	
}
