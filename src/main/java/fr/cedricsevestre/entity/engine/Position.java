package fr.cedricsevestre.entity.engine;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Table(name = "position")
public class Position implements IdProvider, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "position")
	private List<MapTemplate> mapTemplates;

	@Transient
	@Override
	public String getObjectType() {
		return "Position";
	}
	
	public Position(){
		
	}
	public Position(Integer id, String name, List<MapTemplate> mapTemplates) {
		this.id = id;
		this.name = name;
		this.mapTemplates = mapTemplates;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MapTemplate> getMapTemplates() {
		return mapTemplates;
	}
	public void setMapTemplates(List<MapTemplate> mapTemplates) {
		this.mapTemplates = mapTemplates;
	}




}
