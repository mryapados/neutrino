package fr.cedricsevestre.taglib;

import javax.servlet.jsp.JspException;

public interface IIncludeJSP {
	
	public enum Attributes {
		ACTIVEBLOCK("activeBlock"),
		ACTIVEOBJECT("activeObject"),
		BLOCKPREVIEW("blockPreview"),
		FOLDER("folder"),
		PAGE("page"),
		PARENTPAGEBLOCK("parentPageBlock"),
		SURFER("surfer")
		;
	    private final String attribute;
	    private Attributes(final String attribute) {
	        this.attribute = attribute;
	    }
	    @Override
	    public String toString() {
	        return attribute;
	    }
	}
	
	public void getJsp() throws JspException;
	
}
