package fr.cedricsevestre.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * This is a simple tag example to show how content is added to the output
 * stream when a tag is encountered in a JSP page.
 */
public class Hello extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = null;

	/**
	 * Getter/Setter for the attribute name as defined in the tld file for this
	 * tag
	 */
	public void setName(String value) {
		name = value;
	}

	public String getName() {
		return (name);
	}

	/**
	 * doStartTag is called by the JSP container when the tag is encountered
	 */
	public int doStartTag() {
		try {
			JspWriter out = pageContext.getOut();
			out.println("<table border=\"1\">");
			if (name != null)
				out.println("<tr><td> Hello " + name + " </td></tr>");
			else
				out.println("<tr><td> Hello World </td></tr>");
		} catch (Exception ex) {
			throw new Error("All is not well in the world.");
		}
		// Must return SKIP_BODY because we are not supporting a body for this
		// tag.
		return SKIP_BODY;
	}

	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
	public int doEndTag() {
		try {
			JspWriter out = pageContext.getOut();
			out.println("</table>");
		} catch (Exception ex) {
			throw new Error("All is not well in the world.");
		}
		return SKIP_BODY;
	}
}