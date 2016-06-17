package fr.cedricsevestre.entity.engine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class NType implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum ValueType{
		INTEGER, FLOAT, DOUBLE, VARCHAR50, VARCHAR255, TEXT, DATETIME, IMAGE, FILE, HTML, OBJECT, COLLECTION
	}
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ValueType type;
	
	@Column(name = "oftype")
	@Enumerated(EnumType.STRING)
	private ValueType ofType;

	public NType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NType(ValueType type) {
		super();
		this.type = type;
	}

	public NType(ValueType type, ValueType ofType) {
		super();
		this.type = type;
		this.ofType = ofType;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	public ValueType getOfType() {
		return ofType;
	}

	public void setOfType(ValueType ofType) {
		this.ofType = ofType;
	}

	
	
	
}
