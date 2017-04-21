package fr.cedricsevestre.argumentresolver.engine;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;

@Component
@Scope(value = "singleton")
public final class ServerNameHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver  {
	
	@Autowired
	private Common common;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Folder.class);
    }
 
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
		System.out.println("Enter in ServerNameHandlerMethodArgumentResolver");
    	String serverName = nativeWebRequest.getParameter("servername");
		if (serverName == null || serverName.equals("")){
			HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
			serverName = httpServletRequest.getServerName();
		}
		Folder folder =  common.getFolder(serverName);
    	return folder;
    }
 

	
	
	
	
	
	
	
	
	
	
	
	
	

}