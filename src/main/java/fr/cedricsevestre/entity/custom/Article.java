package fr.cedricsevestre.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.translation.Translation;

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
