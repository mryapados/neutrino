package fr.cedricsevestre.entity.engine.translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.custom.Album.AlbumType;

@Entity
@Table(name = "link")
public class Link extends Translation {

	private static final long serialVersionUID = 1L;

	public enum Target{
		BLANC, SELF, PARENT, TOP, FRAMENAME
	}

	@BOField(type = ValueType.ENUM, ofEnum = Target.class, inList = false)
	@Column
	@Enumerated(EnumType.STRING)
	private Target target;
		
	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;
	
	@BOField(type = ValueType.URL)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "url")
	private String url;
	
	@BOField(type = ValueType.IMAGE)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "picto")
	private String picto;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicto() {
		return picto;
	}

	public void setPicto(String picto) {
		this.picto = picto;
	}
	
	
}
