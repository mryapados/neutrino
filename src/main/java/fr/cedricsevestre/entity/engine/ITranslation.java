package fr.cedricsevestre.entity.engine;

public interface ITranslation extends IdProvider {

	Lang getLang();
	void setLang(Lang lang);

	TranslationProvider getTranslation();
	void setTranslation(TranslationProvider translation);
	
}
