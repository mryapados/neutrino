package fr.cedricsevestre.entity.engine;
//package fr.cedricsevestre.entity.back;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.MapKeyColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.annotations.Any;
//import org.hibernate.annotations.AnyMetaDef;
//import org.hibernate.annotations.BatchSize;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.ManyToAny;
//import org.hibernate.annotations.MapKeyType;
//import org.hibernate.annotations.Target;
//import org.hibernate.annotations.Type;
//import org.hibernate.validator.constraints.SafeHtml;
//import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
//import org.hibernate.annotations.MetaValue;
//
//@Entity
//@Table(name = "translation")
//public class Translation2<T> implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
//
////	@Any(metaColumn = @Column(name = "objectType") )
////	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
////			@MetaValue(targetEntity = Template.class, value = "Template"),
////			@MetaValue(targetEntity = Position.class, value = "Position") })
////	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
////	@JoinColumn(name = "id_base")
////	private T translation;
//
//	
////	@Any(metaColumn = @Column(name = "objectType") )
////	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
////			@MetaValue(targetEntity = Template.class, value = "Template"),
////			@MetaValue(targetEntity = Position.class, value = "Position") })
////	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
////	@JoinColumn(name = "id_base")
////	//@OneToMany(mappedBy = "translation")
////	private List<T> translations;
//	
//	
//	@ManyToAny ( 
//	metaColumn =  @Column ( name =  "property_type"  )  ) 
//	@AnyMetaDef ( 
//	    idType =  "integer" , 
//	    metaType =  "string" , 
//	    metaValues =  { 
//	@MetaValue ( value =  "Template" , targetEntity =  Template . class  ), 
//	@MetaValue ( value =  "Position" , targetEntity =  Position . class  )  }  ) 
//	@Cascade (  { org . hibernate . annotations . CascadeType . ALL }  ) 
//	@JoinTable ( name =  "obj_properties" , joinColumns =  @JoinColumn ( name =  "obj_id"  ), 
//	    inverseJoinColumns =  @JoinColumn ( name =  "property_id"  )  ) 
//	public  List < T > translations;
//	
//	
//	
//	
//
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public List<T> getTranslations() {
//		return translations;
//	}
//
//	public void setTranslations(List<T> translations) {
//		this.translations = translations;
//	}
//
////	public T getTranslation() {
////		return translation;
////	}
////
////	public void setTranslation(T translation) {
////		this.translation = translation;
////	}
//
//	
//	
////	public List<T> getTranslations() {
////		return translations;
////	}
////
////	public void setTranslations(List<T> translations) {
////		this.translations = translations;
////	}
//
//}
