package fr.cedricsevestre.entity.engine.translation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.SortType;
import fr.cedricsevestre.annotation.BOField.ValueType;

@Entity
@Table(name = "translation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="object_type")
public abstract class Translation implements ITranslation, Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "object_type", insertable = false, updatable = false)
    private String objectType;
	
	@BOField(type = ValueType.INTEGER, editable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@BOField(type = ValueType.VARCHAR50, defaultField = true, sortBy = SortType.ASC, sortPriority = 200)
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "name")
	private String name;
	
	@BOField(type = ValueType.DATETIME, sortBy = SortType.DESC, sortPriority = 100)
	@NotNull
	@Column(name = "date_added")
	private Date dateAdded;
	
	@BOField(type = ValueType.DATETIME, sortBy = SortType.DESC, sortPriority = 100)
	@NotNull
	@Column(name = "date_updated")
	private Date dateUpdated;
	
	@BOField(type = ValueType.HTML)
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	@Column(name = "description")
	private String description;

	@BOField(type = ValueType.OBJECT)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_lang")
	private Lang lang;
	
	@ManyToOne
	@JoinColumn(name="id_translation")
	private TranslationProvider translation;
	
	public Translation() {
		super();
		this.setDateAdded(new Date());
		this.setDateUpdated(new Date());
	}

	public Translation(Integer id, String name, Date dateAdded, Date dateUpdated, String description, Lang lang) {
		super();
		this.id = id;
		this.name = name;
		this.dateAdded = dateAdded;
		this.dateUpdated = dateUpdated;
		this.description = description;
		this.lang = lang;
	}

	public String getObjectType() {
		return objectType;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	@Override
	public Lang getLang() {
		return lang;
	}

	@Override
	public void setLang(Lang lang) {
		this.lang = lang;
	}

	@Override
	public TranslationProvider getTranslation() {
		return translation;
	}

	@Override
	public void setTranslation(TranslationProvider translation) {
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
		Translation other = (Translation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
