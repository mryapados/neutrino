package fr.cedricsevestre.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Entity
@Table(name = "portfolio")
public class Portfolio extends Page {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@BOField(type = ValueType.INTEGER, defaultValue = "9999")
	@Column(name = "ordered")
	private Integer ordered;

	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;
	
	@BOField(type = ValueType.IMAGE)
	private String image;
	
	@BOField(type = ValueType.TOBJECT)
	//@NotNull
	@ManyToOne
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	public Portfolio() {
		super();
		this.ordered = 9999;
	}

	public Portfolio(String title, String image, Integer ordered) {
		super();
		this.title = title;
		this.image = image;
		this.ordered = ordered;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}


	
	
	
}
