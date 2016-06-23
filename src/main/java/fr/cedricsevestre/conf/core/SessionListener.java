package fr.cedricsevestre.conf.core;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import fr.cedricsevestre.taglib.Block;
 
public class SessionListener implements HttpSessionListener {
	private Logger logger = Logger.getLogger(SessionListener.class);
	
    @Override
    public void sessionCreated(HttpSessionEvent event) {
    	logger.info("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(15*60);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    	logger.info("==== Session is created ====");
    }
}