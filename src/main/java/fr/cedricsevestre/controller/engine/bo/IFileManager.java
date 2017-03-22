package fr.cedricsevestre.controller.engine.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.cedricsevestre.dto.engine.TemplateDto;
import fr.cedricsevestre.entity.engine.IFile;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;

public interface IFileManager {

	

//    listUrl: 'bridges/php/handler.php',
//    uploadUrl: 'bridges/php/handler.php',
//    renameUrl: 'bridges/php/handler.php',
//    copyUrl: 'bridges/php/handler.php',
//    moveUrl: 'bridges/php/handler.php',
//    removeUrl: 'bridges/php/handler.php',
//    editUrl: 'bridges/php/handler.php',
//    getContentUrl: 'bridges/php/handler.php',
//    createFolderUrl: 'bridges/php/handler.php',
//    downloadFileUrl: 'bridges/php/handler.php',
//    downloadMultipleUrl: 'bridges/php/handler.php',
//    compressUrl: 'bridges/php/handler.php',
//    extractUrl: 'bridges/php/handler.php',
//    permissionsUrl: 'bridges/php/handler.php',

	
	
    String add(MultipartFile file, String filename) throws JspException;
    List<IFile> list(Map<String, String> params) throws JspException;
	
	
	
}
