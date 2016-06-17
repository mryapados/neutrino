package fr.cedricsevestre.dto.back;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.Base;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Page;

public class LangDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(length = 5, name = "code")
	private String code;

	public LangDto() {

	}

	public LangDto(Integer id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	public static LangDto from(Lang lang) {
		if (lang == null) return null;
		return new LangDto(lang.getId(), lang.getCode());
	}

	public static Lang to(LangDto langDto) {
		if (langDto == null) return null;
		return new Lang(langDto.getId(), langDto.getCode());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
