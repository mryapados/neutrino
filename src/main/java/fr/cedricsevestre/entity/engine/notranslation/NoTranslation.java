package fr.cedricsevestre.entity.engine.notranslation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.IdProvider;

@Entity
@Table(name = "notranslation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="object_type")
public abstract class NoTranslation implements IdProvider, Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "object_type", insertable = false, updatable = false)
    private String objectType;
	
	@BOField(type = ValueType.INTEGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@BOField(type = ValueType.TEXT)
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "name")
	private String name;
	
	@BOField(type = ValueType.DATETIME)
	@NotNull
	@Column(name = "date_add")
	private Date dateAdd;
	
	@BOField(type = ValueType.HTML)
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	@Column(name = "description")
	private String description;

	public NoTranslation() {
		super();
		this.setDateAdd(new Date());
	}

	public NoTranslation(Integer id, String name, Date dateAdd, String description) {
		super();
		this.id = id;
		this.name = name;
		this.dateAdd = dateAdd;
		this.description = description;
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
		NoTranslation other = (NoTranslation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
