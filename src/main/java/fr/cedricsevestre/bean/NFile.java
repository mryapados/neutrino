package fr.cedricsevestre.bean;

import java.io.Serializable;
import java.util.Date;

import fr.cedricsevestre.entity.engine.independant.objects.File;

public class NFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String rights;
	private Date date;
	private Long size;
	private String type;
	private File tag;
	
	public NFile(String name, String rights, Date date, Long size, String type, File tag) {
		super();
		this.name = name;
		this.rights = rights;
		this.date = date;
		this.size = size;
		this.type = type;
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public File getTag() {
		return tag;
	}
	public void setTag(File tag) {
		this.tag = tag;
	}

	
	
}