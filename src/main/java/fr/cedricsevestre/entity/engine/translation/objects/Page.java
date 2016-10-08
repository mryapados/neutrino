package fr.cedricsevestre.entity.engine.translation.objects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "page")
public class Page extends Translation {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "context")
	private String context;
	
	@OneToOne
	@JoinColumn(name="model")
	private Template model;
	
	public Page() {
		
	}
	
	public Page(Integer id, String name, Date dateAdd, Date dateUpdated, String description, Lang lang, String context, Template model) {
		super(id, name, dateAdd, dateUpdated, description, lang);
		this.context = context;
		this.model = model;
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Template getModel() {
		return model;
	}

	public void setModel(Template model) {
		this.model = model;
	}
	
	
}
