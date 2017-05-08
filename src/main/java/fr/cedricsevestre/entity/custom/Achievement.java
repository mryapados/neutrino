package fr.cedricsevestre.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "achievement")
public class Achievement extends Translation {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@BOField(type = ValueType.INTEGER, defaultValue = "9999")
	@Column(name = "ordered")
	private Integer ordered;
	
	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;

	@BOField(type = ValueType.COLOR)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "color")
	private String color;
	
	@BOField(type = ValueType.INTEGER)
	@Column(name = "value")
	private Integer value;
	
	public Achievement() {
		
	}

	public Achievement(Integer ordered, String title, String color, Integer value) {
		super();
		this.ordered = ordered;
		this.title = title;
		this.color = color;
		this.value = value;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
