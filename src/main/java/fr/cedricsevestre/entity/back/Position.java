package fr.cedricsevestre.entity.back;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position extends Base {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "position")
	private List<MapTemplate> mapTemplates;

	public Position() {
		
	}
	public Position(Integer id, String name, Date dateAdd, String description, List<MapTemplate> mapTemplates) {
		super(id, name, dateAdd, description);
		this.mapTemplates = mapTemplates;
	}

	public List<MapTemplate> getMapTemplates() {
		return mapTemplates;
	}

	public void setMapTemplates(List<MapTemplate> mapTemplates) {
		this.mapTemplates = mapTemplates;
	}

}
