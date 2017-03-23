package fr.cedricsevestre.controller.engine.bo;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.web.multipart.MultipartFile;

import fr.cedricsevestre.entity.engine.independant.objects.File;

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
    //ResultEncapsulator list(Map<String, String> params) throws JspException;
	
	
	
}
