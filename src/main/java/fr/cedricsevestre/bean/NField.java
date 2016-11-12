package fr.cedricsevestre.bean;

import java.io.Serializable;
import java.util.List;

import fr.cedricsevestre.annotation.BOField.SortType;
import fr.cedricsevestre.annotation.BOField.ValueType;

public class NField implements Serializable {

	private static final long serialVersionUID = 1L;

	private ValueType type;
	private ValueType ofType;
	private String name;
	private String className;
	private boolean inList;
	private boolean inView;
	private boolean editable;
	private SortType sortBy;
	private int sortPriority;
	private boolean defaultField;
	private int displayOrder;
	private String tabName;
	private String groupName;
	private List<String> enumDatas;
	
	public NField(ValueType type, ValueType ofType, String name, String className, boolean inList, boolean inView, boolean editable, SortType sortBy, int sortPriority, boolean defaultField, int displayOrder, String tabName, String groupName, List<String> enumDatas) {
		super();
		this.type = type;
		this.ofType = ofType;
		this.name = name;
		this.className = className;
		this.inList = inList;
		this.inView = inView;
		this.editable = editable;
		this.sortBy = sortBy;
		this.sortPriority = sortPriority;
		this.defaultField = defaultField;
		this.displayOrder = displayOrder;
		this.tabName = tabName;
		this.groupName = groupName;
		this.enumDatas = enumDatas;
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
	public boolean isInView() {
		return inView;
	}
	public void setInView(boolean inView) {
		this.inView = inView;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
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
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<String> getEnumDatas() {
		return enumDatas;
	}
	public void setEnumDatas(List<String> enumDatas) {
		this.enumDatas = enumDatas;
	}


}