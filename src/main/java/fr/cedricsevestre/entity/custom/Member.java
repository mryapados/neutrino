package fr.cedricsevestre.entity.custom;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.independant.objects.User;

@Entity
@DiscriminatorValue(value = "member")
public class Member extends User {

	private static final long serialVersionUID = 1L;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "first_name")
	private String firstName;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "last_name")
	private String lastName;

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "nick_name")
	private String nickName;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "mail")
	private String mail;
	
	@Transient
	public String getObjectType() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public String getName() {
		return getLogin();
	}

	@Override
	public void setName(String name) {
		setLogin(name);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	
	
	
}
