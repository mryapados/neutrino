package fr.cedricsevestre.entity;

import fr.cedricsevestre.entity.back.Lang;
import fr.cedricsevestre.entity.back.Translation;

public interface ITranslation {

	Lang getLang();
	void setLang(Lang lang);

	Translation getTranslation();
	void setTranslation(Translation translation);
	
}
