package fr.cedricsevestre.entity.engine;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "translation_provider")
public class TranslationProvider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="translation")
	@MapKey(name = "lang")
	private Map<Lang, Translation> translations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Lang, Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(Map<Lang, Translation> translations) {
		this.translations = translations;
	}

}
