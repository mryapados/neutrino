package fr.cedricsevestre.entity.custom;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.cedricsevestre.entity.engine.Base;

@Entity
@Table(name = "album")
public class Album extends Base {

	private static final long serialVersionUID = 1L;

	public enum AlbumType{
		DEFAULT, OTHER
	}

	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private AlbumType type;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idproject")
	private Project project;
	
	@OneToMany(mappedBy = "album")
	private List<File> files;

	public AlbumType getType() {
		return type;
	}

	public void setType(AlbumType type) {
		this.type = type;
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
