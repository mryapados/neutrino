package fr.cedricsevestre.entity.back;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.back.MapTemplate;

@Entity
@Table(name = "template")
public class Template extends Base {

	private static final long serialVersionUID = 1L;

	// PAGE = Model ; Block = block ; PAGEBLOCK = Set of elements (idem PAGE)
	public enum TemplateType{
		PAGE, BLOCK, PAGEBLOCK
	}
	
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private TemplateType type;
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "path")
	private String path;
	
	@OneToMany(mappedBy = "block")
	private List<MapTemplate> models;

	@OneToMany(mappedBy = "model")
	private List<MapTemplate> blocks;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_description")
	private String metaDescription;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_title")
	private String metaTitle;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_keywords")
	private String metaKeyWords;
	
	public Template() {

	}

	public Template(Integer id, String name, Date dateAdd, String description, TemplateType type, String path,
			List<MapTemplate> models, List<MapTemplate> blocks, String metaDescription, String metaTitle,
			String metaKeyWords) {
		super(id, name, dateAdd, description);
		this.type = type;
		this.path = path;
		this.models = models;
		this.blocks = blocks;
		this.metaDescription = metaDescription;
		this.metaTitle = metaTitle;
		this.metaKeyWords = metaKeyWords;
	}

	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<MapTemplate> getModels() {
		return models;
	}

	public void setModels(List<MapTemplate> models) {
		this.models = models;
	}

	public List<MapTemplate> getBlocks() {
		return blocks;
	}

	public void setBlocs(List<MapTemplate> blocks) {
		this.blocks = blocks;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaKeyWords() {
		return metaKeyWords;
	}

	public void setMetaKeyWords(String metaKeyWords) {
		this.metaKeyWords = metaKeyWords;
	}

	
	
}
