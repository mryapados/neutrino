package fr.cedricsevestre.taglib;

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.tag.common.core.ParamSupport;

public class Param extends ParamSupport  {
	private static final long serialVersionUID = 1L;

    //*********************************************************************
    // Accessor methods
	
	// for tag attribute
	public void setName(String name) throws JspTagException {
	    this.name = name;
	}

	// for tag attribute
	public void setValue(String value) throws JspTagException {
	    this.value = value;
	}
	
}