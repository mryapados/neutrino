package fr.cedricsevestre.dto.engine;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;

public class NoTranslationDto  extends IdProviderDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date dateAdded;
	
	@NotNull
	private Date dateUpdated;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	private String description;
	
	public NoTranslationDto() {

	}

	public NoTranslationDto(String objectType, Integer id, String name, Date dateAdded, Date dateUpdated, String description) {
		super(objectType, id, name);
		this.dateAdded = dateAdded;
		this.dateUpdated = dateUpdated;
		this.description = description;
	}

	public static NoTranslationDto from(NoTranslation noTranslation) {
		return new NoTranslationDto(noTranslation.getObjectType(), noTranslation.getId(), noTranslation.getName(), noTranslation.getDateAdded(), noTranslation.getDateUpdated(), noTranslation.getDescription());
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
