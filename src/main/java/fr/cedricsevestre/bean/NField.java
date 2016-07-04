package fr.cedricsevestre.bean;

import java.io.Serializable;

import fr.cedricsevestre.annotation.BOField.ValueType;

public class NField implements Serializable {

	private static final long serialVersionUID = 1L;

	private ValueType type;
	private ValueType ofType;
	private String name;
	private String objectType;
	private boolean inList;

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
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public boolean isInList() {
		return inList;
	}
	public void setInList(boolean inList) {
		this.inList = inList;
	}

	public NField(ValueType type, ValueType ofType, String name, String objectType, boolean inList) {
		super();
		this.type = type;
		this.ofType = ofType;
		this.name = name;
		this.objectType = objectType;
		this.inList = inList;
	}
}