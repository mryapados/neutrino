package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public interface ICacheSupport {
	
	@Cacheable(value="jspCache")
	StringWriter getCachedValue(int hashCode) throws JspException, IOException;
	
}
