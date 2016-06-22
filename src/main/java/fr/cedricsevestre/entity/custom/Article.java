package fr.cedricsevestre.entity.custom;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.Translation;

@Entity
@Table(name = "album")
public class Article extends Translation {

	private static final long serialVersionUID = 1L;

	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "title")
	private String title;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_description")
	private String metaDescription;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_title")
	private String metaTitle;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_keywords")
	private String metaKeyWords;
	
	
}
