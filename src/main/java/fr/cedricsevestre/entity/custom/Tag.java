package fr.cedricsevestre.entity.custom;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.ValueType;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;


@Entity
@Table(name = "tag")
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "Tag.allJoins", 
		attributeNodes = { 
			@NamedAttributeNode("files")
		})
})
public class Tag extends NoTranslation implements Serializable{

	private static final long serialVersionUID = 1L;

	@BOField(type = ValueType.COLLECTION, ofType = ValueType.TOBJECT)
	@ManyToMany(mappedBy = "tags")
	private List<Media> files;

	public List<Media> getFiles() {
		return files;
	}

	public void setFiles(List<Media> files) {
		this.files = files;
	}
	
}
