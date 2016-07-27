package fr.cedricsevestre.bean;

import java.io.Serializable;

import fr.cedricsevestre.annotation.BOField.SortType;
import fr.cedricsevestre.annotation.BOField.ValueType;

public class NField implements Serializable {

	private static final long serialVersionUID = 1L;

	private ValueType type;
	private ValueType ofType;
	private String name;
	private String className;
	private boolean inList;
	private SortType sortBy;
	private int sortPriority;
	private boolean defaultField;
	
	public NField(ValueType type, ValueType ofType, String name, String className, boolean inList, SortType sortBy, int sortPriority, boolean defaultField) {
		super();
		this.type = type;
		this.ofType = ofType;
		this.name = name;
		this.className = className;
		this.inList = inList;
		this.sortBy = sortBy;
		this.sortPriority = sortPriority;
		this.defaultField = defaultField;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isInList() {
		return inList;
	}
	public void setInList(boolean inList) {
		this.inList = inList;
	}
	public SortType getSortBy() {
		return sortBy;
	}
	public void setSortBy(SortType sortBy) {
		this.sortBy = sortBy;
	}
	public int getSortPriority() {
		return sortPriority;
	}
	public void setSortPriority(int sortPriority) {
		this.sortPriority = sortPriority;
	}
	public boolean isDefaultField() {
		return defaultField;
	}
	public void setDefaultField(boolean defaultField) {
		this.defaultField = defaultField;
	}

}