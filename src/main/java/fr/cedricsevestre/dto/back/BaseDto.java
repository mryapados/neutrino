package fr.cedricsevestre.dto.back;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.Base;
import fr.cedricsevestre.entity.engine.Lang;

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

	private LangDto lang;
	
	public BaseDto() {

	}

	public BaseDto(Integer id, String name, Date dateAdd, String description, LangDto lang) {
		super();
		this.id = id;
		this.name = name;
		this.dateAdd = dateAdd;
		this.description = description;
		this.lang = lang;
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

	public LangDto getLang() {
		return lang;
	}

	public void setLang(LangDto lang) {
		this.lang = lang;
	}


	
}
