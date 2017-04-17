package fr.cedricsevestre.entity.custom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;


@Entity
@Table(name = "icon")
public class Icon extends NoTranslation implements Serializable{

	private static final long serialVersionUID = 1L;

	@BOField(type = ValueType.VARCHAR50)
	private String flatIcon;

	@BOField(type = ValueType.VARCHAR50)
	private String fontAwesome;
	
	@BOField(type = ValueType.VARCHAR50)
	private String glyphIcon;

	public Icon() {

	}
	
	public Icon(String name, String flatIcon, String fontAwesome, String glyphIcon) {
		super();
		this.setName(name);
		this.flatIcon = flatIcon;
		this.fontAwesome = fontAwesome;
		this.glyphIcon = glyphIcon;
	}

	public String getFlatIcon() {
		return flatIcon;
	}

	public void setFlatIcon(String flatIcon) {
		this.flatIcon = flatIcon;
	}

	public String getFontAwesome() {
		return fontAwesome;
	}

	public void setFontAwesome(String fontAwesome) {
		this.fontAwesome = fontAwesome;
	}

	public String getGlyphIcon() {
		return glyphIcon;
	}

	public void setGlyphIcon(String glyphIcon) {
		this.glyphIcon = glyphIcon;
	}
	
	
}
