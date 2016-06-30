package fr.cedricsevestre.dto.engine;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.translation.Translation;

public class TranslationDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String objectType;
	
	private Integer id;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String name;
	
	@NotNull
	private Date dateAdd;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	private String description;

	private LangDto lang;
	
	public TranslationDto() {

	}

	public TranslationDto(String objectType, Integer id, String name, Date dateAdd, String description, LangDto lang) {
		super();
		this.objectType = objectType;
		this.id = id;
		this.name = name;
		this.dateAdd = dateAdd;
		this.description = description;
		this.lang = lang;
	}

	public static TranslationDto from(Translation translation) {
		return new TranslationDto(translation.getObjectType(), translation.getId(), translation.getName(), translation.getDateAdd(), translation.getDescription(), LangDto.from(translation.getLang()));
	}

	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
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

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LangDto getLang() {
		return lang;
	}

	public void setLang(LangDto lang) {
		this.lang = lang;
	}


	
}
