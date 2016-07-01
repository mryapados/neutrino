package fr.cedricsevestre.bean;

import java.io.Serializable;

import fr.cedricsevestre.annotation.BOField.ValueType;

public class NField implements Serializable { 

	private static final long serialVersionUID = 1L;
	
	private ValueType type;
	private ValueType ofType;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NField(ValueType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}
	public NField(ValueType type, ValueType ofType, String name) {
		super();
		this.type = type;
		this.ofType = ofType;
		this.name = name;
	}
	

	
	
	
	
}