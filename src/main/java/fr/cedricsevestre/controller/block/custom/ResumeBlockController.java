package fr.cedricsevestre.controller.block.custom;

import java.util.List;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.annotation.BlockController;
import fr.cedricsevestre.annotation.BlockMapping;
import fr.cedricsevestre.entity.custom.Category;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.custom.Skill;
import fr.cedricsevestre.entity.custom.Skill.SkillKind;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.CategoryService;
import fr.cedricsevestre.service.custom.ExperienceService;
import fr.cedricsevestre.service.custom.SkillService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;

@BlockController
@Component
public class ResumeBlockController {

	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ExperienceService experienceService;
	
	@BlockMapping(value = "@bo_block_list")
	public ModelMap testage(Page page, Translation model, Translation activeObject, Template template, PageContext pageContext){
		System.out.println("JE SUIS DANS TESTAGE !!!! template = " + template.getName() + " - objectType = " + pageContext.getAttribute("objectType", PageContext.REQUEST_SCOPE));
		
		
		pageContext.setAttribute("bonjourPage", "bonjour PAGE_SCOPE", PageContext.PAGE_SCOPE);
		pageContext.setAttribute("bonjourRequest", "bonjour REQUEST_SCOPE", PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("bonjourSession", "bonjour SESSION_SCOPE", PageContext.SESSION_SCOPE);
		
		
		return null;
	}
	
	
	@BlockMapping({"resume_block_nav", "resume_block_listpage"})
	public ModelMap navs(Translation model, Translation activeObject, Template template, Folder folder, Lang lang) throws ControllerException{	
		try {
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("categories", categoryService.findAllForFolderAndLang(folder, lang));
			return modelMap;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}

	}
	
	
	
	@BlockMapping("resume_block_skillsProgressBar")
	public ModelMap skillsProgressBar(Folder folder, Lang lang, PageContext pageContext) throws ControllerException{	
		try {
			ModelMap modelMap = new ModelMap();
			Resume resume = (Resume) pageContext.getAttribute("activeResume", PageContext.REQUEST_SCOPE);
			if (resume != null)	modelMap.addAttribute("skills", skillService.findAllKindForResumeAndFolderAndLang(resume, SkillKind.PROGRESSBAR, folder, lang));
			return modelMap;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}

	}
	
	@BlockMapping("resume_block_skillsChart")
	public ModelMap skillsChart(Folder folder, Lang lang, PageContext pageContext) throws ControllerException{	
		try {
			ModelMap modelMap = new ModelMap();
			Resume resume = (Resume) pageContext.getAttribute("activeResume", PageContext.REQUEST_SCOPE);
			if (resume != null)	modelMap.addAttribute("skills", skillService.findAllKindForResumeAndFolderAndLang(resume, SkillKind.CHART, folder, lang));
			return modelMap;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}
	
	
	@BlockMapping("resume_block_experiences")
	public ModelMap experiences(Folder folder, Lang lang, PageContext pageContext) throws ControllerException{	
		try {
			ModelMap modelMap = new ModelMap();
			Resume resume = (Resume) pageContext.getAttribute("activeResume", PageContext.REQUEST_SCOPE);
			if (resume != null)	modelMap.addAttribute("experiences", experienceService.findAllForResumeAndFolderAndLang(resume, folder, lang));
			return modelMap;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}
	
	
}
