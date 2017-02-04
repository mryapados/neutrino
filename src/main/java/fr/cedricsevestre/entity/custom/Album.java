package fr.cedricsevestre.entity.custom;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "album")
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "Album.allJoins", 
		attributeNodes = { 
			@NamedAttributeNode("files")
		})
})
public class Album extends Translation {

	private static final long serialVersionUID = 1L;

	public enum AlbumType{
		DEFAULT, OTHER
	}

	@BOField(type = ValueType.VARCHAR50)
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private AlbumType albumType;
	
	@BOField(type = ValueType.TOBJECT)
	//@NotNull
	@ManyToOne
	@JoinColumn(name="idproject")
	private Project project;
	
	@BOField(type = ValueType.COLLECTION, ofType = ValueType.TOBJECT)
	@OneToMany(mappedBy = "album")
	private List<File> files;

	public AlbumType getAlbumType() {
		return albumType;
	}

	public void setAlbumType(AlbumType type) {
		this.albumType = type;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	
	
}
