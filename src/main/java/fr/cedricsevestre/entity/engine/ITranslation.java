package fr.cedricsevestre.entity.engine;

public interface ITranslation {

	Lang getLang();
	void setLang(Lang lang);

	Translation getTranslation();
	void setTranslation(Translation translation);
	
}
