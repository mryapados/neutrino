package fr.cedricsevestre.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NData implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<NField> fields;
	private List<Map<String, Object>> datas;
	
	public NData(List<NField> fields, List<Map<String, Object>> datas) {
		super();
		this.fields = fields;
		this.datas = datas;
	}

	public List<NField> getFields() {
		return fields;
	}

	public void setFields(List<NField> fields) {
		this.fields = fields;
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}