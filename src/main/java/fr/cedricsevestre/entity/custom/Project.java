package fr.cedricsevestre.entity.custom;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "project")
public class Project extends Translation {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "project")
	private List<Album> albums;

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
}
