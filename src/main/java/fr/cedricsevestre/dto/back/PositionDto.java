package fr.cedricsevestre.dto.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Template.TemplateType;
import fr.cedricsevestre.taglib.Block;

public class PositionDto {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String name;
	
	public PositionDto() {

	}

	private List<MapTemplateDto> mapTemplateDtos;
	
	public PositionDto(Integer id, String name, List<MapTemplateDto> mapTemplateDtos) {
		this.id = id;
		this.name = name;
		this.mapTemplateDtos = mapTemplateDtos;
	}

	public static PositionDto from(Position position) {
		List<MapTemplateDto> mapTemplateDtos = new ArrayList<>();
		for (MapTemplate mapTemplate : position.getMapTemplates()) {
			mapTemplateDtos.add(MapTemplateDto.from(mapTemplate));
		}
		return new PositionDto(position.getId(), position.getName(), mapTemplateDtos);
	}
	
	public static PositionDto fromWithoutMapTemplate(Position position) {
		return new PositionDto(position.getId(), position.getName(), new ArrayList<>());
	}

	public static Position to(PositionDto positionDto){
		List<MapTemplate> mapTemplates = new ArrayList<>();
		for (MapTemplateDto mapTemplateDto : positionDto.getMapTemplateDtos()) {
			mapTemplates.add(MapTemplateDto.to(mapTemplateDto, positionDto));
		}
		return new Position(positionDto.getId(), positionDto.getName(), mapTemplates);
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

	public List<MapTemplateDto> getMapTemplateDtos() {
		return mapTemplateDtos;
	}

	public void setMapTemplateDtos(List<MapTemplateDto> mapTemplateDtos) {
		this.mapTemplateDtos = mapTemplateDtos;
	}






	
}
