package fr.cedricsevestre.entity.engine.independant.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "status")
@DiscriminatorValue(value = "user")
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;


//	public enum PreviewType{
//		NONE, ADMIN, USER
//	}
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_WEBMASTER = "ROLE_WEBMASTER";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_PUBLIC = "ROLE_PUBLIC";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Column(name = "username")
	private String login;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Column(name = "encrypted_password")
	private String encryptedPassword;

	@NotNull
	@Column(name = "enabled")
	private Boolean enabled;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Column(name = "role")
	private String role;
//
//	@Transient
//	private boolean preview;
	
	@Transient
	private String name;
	
	public abstract String getName();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptedPassword = encryptPassword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public PreviewType getPreview() {
//		if (preview){
//			if (role.equals(ROLE_ADMIN)) return PreviewType.ADMIN;
//			else return PreviewType.USER;
//		} else return PreviewType.NONE;
//	}
//
//	public void setPreview(Boolean preview) {
//		this.preview = preview;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	


}
