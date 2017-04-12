package fr.cedricsevestre.entity.custom;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.Link;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "curriculum_vitae")

@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "CurriculumVitae.allJoins", 
		attributeNodes = { 
			@NamedAttributeNode("links")
		})
})

public class CurriculumVitae extends Translation {

	private static final long serialVersionUID = 1L;

	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "first_name")
	private String firstName;
	
	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "last_name")
	private String lastName;

	@BOField(type = ValueType.VARCHAR50)
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "mail")
	private String mail;
	
	@BOField(type = ValueType.FILE)
	private String picture;
	
	@BOField(type = ValueType.FILE)
	private String downloadableFile;
	
	@BOField(type = ValueType.COLLECTION, ofType = ValueType.TOBJECT)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="curiculum_vitae_links", joinColumns=@JoinColumn(name="link_id"))
	@Column(name = "links")
	private List<Link> links;

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDownloadableFile() {
		return downloadableFile;
	}

	public void setDownloadableFile(String downloadableFile) {
		this.downloadableFile = downloadableFile;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	
	
}
