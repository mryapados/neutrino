package fr.cedricsevestre.dto.back;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.back.Base;
import fr.cedricsevestre.entity.back.Lang;

public class BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String name;
	
	private Date dateAdd;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	private String description;

	private LangDto langDto;
	
	public BaseDto() {

	}

	public BaseDto(Integer id, String name, Date dateAdd, String description, LangDto langDto) {
		super();
		this.id = id;
		this.name = name;
		this.dateAdd = dateAdd;
		this.description = description;
		this.langDto = langDto;
	}

	public static BaseDto from(Base base) {
		return new BaseDto(base.getId(), base.getName(), base.getDateAdd(), base.getDescription(), LangDto.from(base.getLang()));
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

	public LangDto getLangDto() {
		return langDto;
	}

	public void setLangDto(LangDto langDto) {
		this.langDto = langDto;
	}

//	public static Base to(BaseDto baseDto) {
//		return new Base(baseDto.getId(), baseDto.getName(), baseDto.getDateAdd(), baseDto.getDescription());
//	}

	
}
