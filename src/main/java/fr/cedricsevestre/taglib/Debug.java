package fr.cedricsevestre.taglib;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;

@Component
@Scope(value = "singleton")
public class Debug extends TagSupport  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Debug.class);
			
	private static Common common;
	@Autowired
	public void Common(Common common) {
		Debug.common = common;
	}
	
	public int doStartTag() {
		if (common.DEBUG){
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}
	

}