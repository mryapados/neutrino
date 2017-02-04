package fr.cedricsevestre.entity.engine.translation.objects;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Entity
@Table(name = "template")
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "Template.allJoins", 
		attributeNodes = { 
			@NamedAttributeNode("models"),
			@NamedAttributeNode("blocks")
		})
})
public class Template extends Translation {

	private static final long serialVersionUID = 1L;

	// PAGE = Model ; Block = block ; PAGEBLOCK = Set of elements (idem PAGE)
	public enum TemplateKind{
		PAGE, BLOCK, PAGEBLOCK
	}
	
	@BOField(type = ValueType.ENUM, ofEnum = TemplateKind.class, inList = false)
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private TemplateKind kind;
	
	@BOField(type = ValueType.VARCHAR255, inList = false)
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "path")
	private String path;

	@BOField(type = ValueType.COLLECTION, ofType = ValueType.OBJECT)
	@OneToMany(mappedBy = "block")
	@Cascade(CascadeType.DELETE)
	private Set<MapTemplate> models;

	@BOField(type = ValueType.COLLECTION, ofType = ValueType.OBJECT)
	@OneToMany(mappedBy = "model")
	@Cascade(CascadeType.DELETE)
	private Set<MapTemplate> blocks;
	
	@BOField(type = ValueType.TEXT, tabName = "01_seo", groupName = "02_group2")
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_description")
	private String metaDescription;
	
	@BOField(type = ValueType.TEXT, tabName = "01_seo", groupName = "01_group1")
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_title")
	private String metaTitle;
	
	@BOField(type = ValueType.TEXT, tabName = "01_seo", groupName = "01_group1")
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "meta_keywords")
	private String metaKeyWords;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_schema")
	private NSchema schema;
	
	@OneToMany(mappedBy = "template")
	private List<NData> datas;
	
	public Template() {
		super();
	}

	public Template(Integer id, String name, Date dateAdd, Date dateUpdated, String description, Lang lang, TemplateKind type, String path,
			Set<MapTemplate> models, Set<MapTemplate> blocks, String metaDescription, String metaTitle,
			String metaKeyWords) {
		super(id, name, dateAdd, dateUpdated, description, lang);
		this.kind = type;
		this.path = path;
		this.models = models;
		this.blocks = blocks;
		this.metaDescription = metaDescription;
		this.metaTitle = metaTitle;
		this.metaKeyWords = metaKeyWords;
	}
	
	

	public TemplateKind getKind() {
		return kind;
	}

	public void setKind(TemplateKind kind) {
		this.kind = kind;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<MapTemplate> getModels() {
		return models;
	}

	public void setModels(Set<MapTemplate> models) {
		this.models = models;
	}

	public Set<MapTemplate> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<MapTemplate> blocks) {
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

	public NSchema getSchema() {
		return schema;
	}

	public void setSchema(NSchema schema) {
		this.schema = schema;
	}

	public List<NData> getDatas() {
		return datas;
	}

	public void setDatas(List<NData> datas) {
		this.datas = datas;
	}

	

	

	

	
	
}
