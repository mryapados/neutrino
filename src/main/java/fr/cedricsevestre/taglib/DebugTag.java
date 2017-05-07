package fr.cedricsevestre.taglib;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.constants.AttributeConst;

public class DebugTag extends TagSupport  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(DebugTag.class);
	
	public int doStartTag() {
		CommonUtil commonUtil = (CommonUtil) pageContext.getAttribute(AttributeConst.COMMON_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
		if (CommonUtil.DEBUG){
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}
	

}