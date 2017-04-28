//package fr.cedricsevestre.dto.custom;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.SafeHtml;
//import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
//
//import fr.cedricsevestre.annotation.BOField;
//import fr.cedricsevestre.annotation.BOField.SortType;
//import fr.cedricsevestre.annotation.BOField.ValueType;
//import fr.cedricsevestre.entity.engine.independant.objects.Folder;
//
//public class ContactDto implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private Integer id;
//
//	@NotNull
//	private String name;
//	
//	private Date dateAdded;
//	
//	private Date dateUpdated;
//
//	@NotNull
//	private String email;
//	
//	@NotNull
//	private String subject;
//	
//	@NotNull
//	private String message;
//	
//	private Folder folder;
//
//	public ContactDto() {
//
//	}
//
//	public ContactDto(Integer id, String name, Date dateAdded, Date dateUpdated, String email, String subject, String message, Folder folder) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.dateAdded = dateAdded;
//		this.dateUpdated = dateUpdated;
//		this.email = email;
//		this.subject = subject;
//		this.message = message;
//		this.folder = folder;
//	}
//
//
//
//	public Integer getModelId() {
//		return modelId;
//	}
//
//	public void setModelId(Integer modelId) {
//		this.modelId = modelId;
//	}
//
//	public Integer getBlockId() {
//		return blockId;
//	}
//
//	public void setBlockId(Integer blockId) {
//		this.blockId = blockId;
//	}
//
//	public Integer getPositionId() {
//		return positionId;
//	}
//
//	public void setPositionId(Integer positionId) {
//		this.positionId = positionId;
//	}
//
//	public Integer getOrdered() {
//		return ordered;
//	}
//
//	public void setOrdered(Integer ordered) {
//		this.ordered = ordered;
//	}
//
//	@Override
//	public String toString() {
//		return "MapTemplateSimpleDto [modelId=" + modelId + ", blockId=" + blockId + ", positionId=" + positionId + ", ordered=" + ordered + "]";
//	}
//
//	
//
//
//	
//	
//}
