package fr.cedricsevestre.entity.front;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cedricsevestre.entity.front.Base;

@Entity
@Table(name = "project")
public class Project extends Base {

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
