package fr.cedricsevestre.entity.engine.independant.objects;

import java.io.Serializable;
import java.util.List;

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

import fr.cedricsevestre.entity.engine.IdProvider;

@Entity
@Table(name = "folder")
public class Folder implements IdProvider, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "name")
	private String name;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "servername")
	private String serverName;

	@Transient
	@Override
	public String getObjectType() {
		return "folder";
	}
	
	public Folder(){
		
	}
	public Folder(Integer id, String name, String serverName) {
		this.id = id;
		this.name = name;
		this.serverName = serverName;
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
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
