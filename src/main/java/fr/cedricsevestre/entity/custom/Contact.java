package fr.cedricsevestre.entity.custom;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.SortType;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;


@Entity
@Table(name = "contact")
public class Contact implements IdProvider, Serializable{

	private static final long serialVersionUID = 1L;

	@BOField(type = ValueType.INTEGER, editable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	private Integer id;

	@BOField(type = ValueType.VARCHAR50, defaultField = true, sortBy = SortType.ASC, sortPriority = 200)
	@NotNull
	@NotEmpty
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
	
	@BOField(type = ValueType.VARCHAR50)
	@NotNull
	@NotEmpty
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "email")
	private String email;
	
	@BOField(type = ValueType.VARCHAR50)
	@NotNull
	@NotEmpty
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "subject")
	private String subject;
	
	@BOField(type = ValueType.VARCHAR255)
	@NotNull
	@NotEmpty
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "message")
	private String message;
	
	@JsonIgnore
	@BOField(type = ValueType.OBJECT)
	@OneToOne
	private Folder folder;

	public Contact() {
		super();
		this.setDateAdded(new Date());
		this.setDateUpdated(new Date());
	}
	
	public Contact(String name, String email, String subject, String message, Folder folder) {
		super();
		this.setDateAdded(new Date());
		this.setDateUpdated(new Date());
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.folder = folder;
	}
	
	@Override
	public String getObjectType() {
		return this.getClass().getSimpleName();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

}
