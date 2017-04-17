package fr.cedricsevestre.entity.custom;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@Entity
@Table(name = "category")
//@NamedEntityGraphs({
//	@NamedEntityGraph(
//		name = "Project.allJoins", 
//		attributeNodes = { 
//			@NamedAttributeNode("albums")
//		})
//})
public class Category extends Page {

	private static final long serialVersionUID = 1L;
	
	@BOField(type = ValueType.COLOR)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "menu_color")
	private String menuColor;

	@BOField(type = ValueType.VARCHAR50)
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;
	
	@BOField(type = ValueType.NTOBJECT)
	@JoinColumn(name="icon_id")
	@OneToOne
	private Icon icon;
	
	public Category() {
		super();
	}

	public Category(String menuColor, String title, Icon icon) {
		super();
		this.menuColor = menuColor;
		this.title = title;
		this.icon = icon;
	}
	
	public String getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	
	
	
	
	
}
