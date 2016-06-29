package fr.cedricsevestre.entity.custom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.cedricsevestre.entity.engine.NoTranslation;

@Entity
@Table(name = "marker")
public class Marker extends NoTranslation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "latitude")
	private Double latitude;

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
	
	
	
}
