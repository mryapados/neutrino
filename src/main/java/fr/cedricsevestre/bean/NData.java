package fr.cedricsevestre.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import fr.cedricsevestre.entity.engine.IdProvider;

public class NData<T extends IdProvider> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<NField> fields;
	private Page<T> objectDatas;
	
	public NData(List<NField> fields, Page<T> objectDatas) {
		super();
		this.fields = fields;
		this.objectDatas = objectDatas;
	}

	public List<NField> getFields() {
		return fields;
	}

	public void setFields(List<NField> fields) {
		this.fields = fields;
	}

	public Page<T> getObjectDatas() {
		return objectDatas;
	}

	public void setObjectDatas(Page<T> objectDatas) {
		this.objectDatas = objectDatas;
	}



	
}