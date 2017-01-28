package fr.cedricsevestre.entity.custom;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "project")
public class Project extends Translation {

	private static final long serialVersionUID = 1L;
	
	@BOField(type = ValueType.HTML, editable = false)
	@SafeHtml(whitelistType = WhiteListType.BASIC)
	@Column(name = "testage")
	private String testage;
	
	
	@BOField(type = ValueType.OBJECT)
	@OneToOne
	private Folder folder;
		
	@BOField(type = ValueType.COLLECTION, ofType = ValueType.TOBJECT)
	@OneToMany(mappedBy = "project")
	private List<Album> albums;

	
	
	
	public String getTestage() {
		return testage;
	}

	public void setTestage(String testage) {
		this.testage = testage;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
}
