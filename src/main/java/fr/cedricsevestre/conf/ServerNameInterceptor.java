package fr.cedricsevestre.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;

@Deprecated
@Component
public class ServerNameInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private CommonUtil common;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("ServerNameInterceptor: REQUEST Intercepted for URI: " + request.getRequestURI());
		String serverName = request.getParameter("servername");
		if (serverName == null){
			serverName = request.getServerName();
		}
		System.out.println("ServerNameInterceptor: serverName = " + serverName);
		request.setAttribute("serverName", serverName);
		return true;
	}
}
