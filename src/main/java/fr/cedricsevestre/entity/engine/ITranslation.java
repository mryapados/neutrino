package fr.cedricsevestre.entity.engine;

public interface ITranslation {

	Lang getLang();
	void setLang(Lang lang);

	TranslationProvider getTranslation();
	void setTranslation(TranslationProvider translation);
	
}
