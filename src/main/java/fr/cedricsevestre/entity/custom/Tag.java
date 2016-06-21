package fr.cedricsevestre.entity.custom;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.NoTranslation;
import fr.cedricsevestre.entity.engine.TranslationProvider;

@Entity
@Table(name = "tag")
public class Tag extends NoTranslation implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "tags")
	private List<File> files;

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
}
