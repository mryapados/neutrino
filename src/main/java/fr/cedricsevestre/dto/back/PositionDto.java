package fr.cedricsevestre.dto.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.back.Template.TemplateType;
import fr.cedricsevestre.taglib.Block;

public class PositionDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	
	public PositionDto() {

	}

	private List<MapTemplateDto> mapTemplateDtos;
	
	public PositionDto(Integer id, String name, Date dateAdd, String description, List<MapTemplateDto> mapTemplateDtos) {
		super(id, name, dateAdd, description);
		this.mapTemplateDtos = mapTemplateDtos;
	}

	public static PositionDto from(Position position) {
		List<MapTemplateDto> mapTemplateDtos = new ArrayList<>();
		for (MapTemplate mapTemplate : position.getMapTemplates()) {
			mapTemplateDtos.add(MapTemplateDto.from(mapTemplate));
		}
		return new PositionDto(position.getId(), position.getName(), position.getDateAdd(), position.getDescription(), mapTemplateDtos);
	}
	
	public static PositionDto fromWithoutMapTemplate(Position position) {
		return new PositionDto(position.getId(), position.getName(), position.getDateAdd(), position.getDescription(), new ArrayList<>());
	}

	public static Position to(PositionDto positionDto){
		List<MapTemplate> mapTemplates = new ArrayList<>();
		for (MapTemplateDto mapTemplateDto : positionDto.getMapTemplateDtos()) {
			mapTemplates.add(MapTemplateDto.to(mapTemplateDto, positionDto));
		}
		return new Position(positionDto.getId(), positionDto.getName(), positionDto.getDateAdd(), positionDto.getDescription(), mapTemplates);
	}

	public List<MapTemplateDto> getMapTemplateDtos() {
		return mapTemplateDtos;
	}

	public void setMapTemplateDtos(List<MapTemplateDto> mapTemplateDtos) {
		this.mapTemplateDtos = mapTemplateDtos;
	}




	
}
