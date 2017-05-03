package fr.cedricsevestre.argumentresolver.engine;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;

@Component
public final class ServerNameHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver  {
	
	private Logger logger = Logger.getLogger(ServerNameHandlerMethodArgumentResolver.class);
	
	@Autowired
	private CommonUtil common;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Folder.class);
    }
 
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
		logger.debug("Enter in resolveArgument");
		boolean forced = true;
    	String serverName = nativeWebRequest.getParameter("servername");
		if (serverName == null || serverName.equals("")){
			forced = false;
			HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
			serverName = httpServletRequest.getServerName();
		}
		Folder folder =  common.getFolder(serverName);
		folder.setServerNameForced(forced);
    	return folder;
    }
 

	
	
	
	
	
	
	
	
	
	
	
	
	

}