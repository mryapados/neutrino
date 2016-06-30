package fr.cedricsevestre.entity.engine.translation;

import fr.cedricsevestre.entity.engine.IdProvider;

public interface ITranslation extends IdProvider {

	Lang getLang();
	void setLang(Lang lang);

	TranslationProvider getTranslation();
	void setTranslation(TranslationProvider translation);
	
}
