package fr.cedricsevestre.controller.template.custom;

import javax.servlet.jsp.PageContext;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.annotation.TemplateController;
import fr.cedricsevestre.annotation.BlockMapping;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@TemplateController
@Component
public class TestBlockController {

	@BlockMapping(value = "@bo_block_list")
	public ModelMap testage(Translation model, Translation activeObject, Template template, PageContext pageContext){
		System.out.println("JE SUIS DANS TESTAGE !!!! template = " + template.getName() + " - objectType = " + pageContext.getAttribute("objectType", PageContext.REQUEST_SCOPE));
		
		
		pageContext.setAttribute("bonjourPage", "bonjour PAGE_SCOPE", PageContext.PAGE_SCOPE);
		pageContext.setAttribute("bonjourRequest", "bonjour REQUEST_SCOPE", PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("bonjourSession", "bonjour SESSION_SCOPE", PageContext.SESSION_SCOPE);
		
		
		return null;
	}
	
}
