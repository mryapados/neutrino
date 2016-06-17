package fr.cedricsevestre.entity.custom;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.cedricsevestre.entity.engine.Base;

@Entity
@Table(name = "file")
public class File extends Base {

	private static final long serialVersionUID = 1L;

	public enum FileType{
		VIDEO, IMAGE
	}
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "altitude")
	private Double altitude;
	
	@Column(name = "date_shooting")
	private Date dateShooting;
	
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private FileType type;
	
	@NotNull
	@Column
	private Integer fileZize;	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "mark", 
		joinColumns = { 
			@JoinColumn(name = "idfile", 
			referencedColumnName = "id")}, 
		inverseJoinColumns = {
			@JoinColumn(name = "idtag", 
			referencedColumnName = "id")})
	private List<Tag> tags;

	@NotNull
	@ManyToOne
	@JoinColumn(name="idalbum")
	private Album album;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Date getDateShooting() {
		return dateShooting;
	}

	public void setDateShooting(Date dateShooting) {
		this.dateShooting = dateShooting;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public Integer getFileZize() {
		return fileZize;
	}

	public void setFileZize(Integer fileZize) {
		this.fileZize = fileZize;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
}
