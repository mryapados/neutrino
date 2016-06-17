package fr.cedricsevestre.entity.engine;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Table(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Base implements ITranslation, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "date_add")
	private Date dateAdd;
	
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	@Column(name = "description")
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_lang")
	private Lang lang;
	
	@ManyToOne
	@JoinColumn(name="id_translation")
	private Translation translation;
	
	
	
	public Base() {
		super();
		this.setDateAdd(new Date());
	}

	public Base(Integer id, String name, Date dateAdd, String description, Lang lang) {
		super();
		this.id = id;
		this.name = name;
		this.dateAdd = dateAdd;
		this.description = description;
		this.lang = lang;
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

	public Lang getLang() {
		return lang;
	}

	public void setLang(Lang lang) {
		this.lang = lang;
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
