package fr.cedricsevestre.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "skill")
public class Skill extends Translation {

	private static final long serialVersionUID = 1L;
	
	public enum SkillKind{
		PROGRESSBAR, CHART
	}
	
	@BOField(type = ValueType.ENUM, ofEnum = SkillKind.class, inList = false)
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private SkillKind kind;
	
	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;
	
	@NotNull
	@BOField(type = ValueType.INTEGER)
	@Column(name = "percent")
	private Integer percent;
	
	@BOField(type = ValueType.COLOR)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "color")
	private String color;

	@NotNull
	@BOField(type = ValueType.INTEGER, defaultValue = "9999", editable = false)
	@Column(name = "ordered")
	private Integer ordered;
	
	
	
	public Skill() {

	}

	public Skill(SkillKind kind, String title, Integer percent, String color) {
		super();
		this.kind = kind;
		this.title = title;
		this.percent = percent;
		this.color = color;
		this.ordered = 9999;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public SkillKind getKind() {
		return kind;
	}

	public void setKind(SkillKind kind) {
		this.kind = kind;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	

}
