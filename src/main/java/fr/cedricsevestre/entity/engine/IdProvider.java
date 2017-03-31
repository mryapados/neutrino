package fr.cedricsevestre.entity.engine;

public interface IdProvider {
	
	String getObjectType();
	
	Integer getId();
	void setId(Integer id);
	
	String getName();
	void setName(String name);
}
