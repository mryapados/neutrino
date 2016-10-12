package fr.cedricsevestre.dto.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateType;

public class TemplateDto extends TranslationDto {
	private static final long serialVersionUID = 1L;

	@NotNull
	private TemplateType type;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String path;

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String metaDescription;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String metaTitle;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String metaKeyWords;

	public TemplateDto() {

	}

	public TemplateDto(String objectType, Integer id, String name, Date dateAdded, Date dateUpdated, String description, LangDto langDto, TemplateType type, String path,
			String metaDescription, String metaTitle, String metaKeyWords) {
		super(objectType, id, name, dateAdded, dateUpdated, description, langDto);
		this.type = type;
		this.path = path;
		this.metaDescription = metaDescription;
		this.metaTitle = metaTitle;
		this.metaKeyWords = metaKeyWords;
	}

	public static TemplateDto from(Template template) {
		return new TemplateDto(template.getObjectType(), template.getId(), template.getName(), template.getDateAdded(), template.getDateUpdated(), template.getDescription(), LangDto.from(template.getLang()), 
				template.getType(), template.getPath(), template.getMetaDescription(), template.getMetaTitle(),
				template.getMetaKeyWords());
	}

	public static Template to(TemplateDto templateDto){
		return new Template(templateDto.getId(), templateDto.getName(), templateDto.getDateAdded(), templateDto.getDateUpdated(), templateDto.getDescription(), LangDto.to(templateDto.getLang()), templateDto.getType(), templateDto.getPath(), new HashSet<MapTemplate>(), new HashSet<MapTemplate>(), templateDto.getMetaDescription(), templateDto.getMetaTitle(), templateDto.getMetaKeyWords());
	}

	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaKeyWords() {
		return metaKeyWords;
	}

	public void setMetaKeyWords(String metaKeyWords) {
		this.metaKeyWords = metaKeyWords;
	}

}
