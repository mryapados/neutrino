package fr.cedricsevestre.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fr.cedricsevestre.common.Common;

@Component
public class ServerNameInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private Common common;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("ServerNameInterceptor: REQUEST Intercepted for URI: " + request.getRequestURI());
		String serverName = request.getParameter("servername");
		if (serverName == null){
			serverName = request.getServerName();
			//request.setAttribute("folder", common.getFolder(serverName));
		}
		return true;
	}
}
