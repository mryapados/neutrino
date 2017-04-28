package fr.cedricsevestre.controller.block.engine.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.annotation.TemplateController;
import fr.cedricsevestre.annotation.BlockMapping;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;

@TemplateController
@Component
public class BOBlockController {
	
	private Logger logger = Logger.getLogger(BOBlockController.class);
	
	@Autowired
	private BackOfficeService backOfficeService;
	
	@BlockMapping(value = "@bo_block_menu_objects")
	public ModelMap boBlockMenuObjects(){
		logger.debug("Enter in 'boBlockMenuObjects'");
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("translationLinks", backOfficeService.getListTranslationObjectType());
		modelMap.addAttribute("noTranslationLinks", backOfficeService.getListNoTranslationObjectType());
		return modelMap;
	}
	
	
}
