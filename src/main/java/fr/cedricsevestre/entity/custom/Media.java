package fr.cedricsevestre.entity.custom;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "media")
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "Media.allJoins", 
		attributeNodes = { 
			@NamedAttributeNode("tags"),
			@NamedAttributeNode("files")
		})
})
public class Media extends Translation {

	private static final long serialVersionUID = 1L;

	public enum FileType{
		VIDEO, IMAGE
	}
	
	@BOField(type = ValueType.DOUBLE)
	@Column(name = "longitude")
	private Double longitude;
	
	@BOField(type = ValueType.DOUBLE)
	@Column(name = "latitude")
	private Double latitude;
	
	@BOField(type = ValueType.DOUBLE)
	@Column(name = "altitude")
	private Double altitude;
	
	@BOField(type = ValueType.DATETIME)
	@Column(name = "date_shooting")
	private Date dateShooting;
	
	@BOField(type = ValueType.ENUM, ofEnum = FileType.class, inList = false)
	@NotNull
	@Column(name = "filetype")
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@BOField(type = ValueType.FILE)
	private String file;
	
	@BOField(type = ValueType.VARCHAR255)
	private String alt;
	
	@BOField(type = ValueType.COLLECTION, ofType = ValueType.FILE)
	@ElementCollection
	@CollectionTable(name="media_files", joinColumns=@JoinColumn(name="idfile"))
	@Column(name="files")
	private Set<String> files;
	
	@BOField(type = ValueType.COLLECTION, ofType = ValueType.NTOBJECT)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "mark", 
		joinColumns = { 
			@JoinColumn(name = "idfile", 
			referencedColumnName = "id")}, 
		inverseJoinColumns = {
			@JoinColumn(name = "idtag", 
			referencedColumnName = "id")})
	private Set<Tag> tags;

	@BOField(type = ValueType.TOBJECT)
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

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Set<String> getFiles() {
		return files;
	}

	public void setFiles(Set<String> files) {
		this.files = files;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	
	
}
