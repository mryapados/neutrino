package fr.cedricsevestre.conf.core;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import fr.cedricsevestre.conf.ApplicationConfiguration;
import fr.cedricsevestre.conf.ApplicationProperties;
import fr.cedricsevestre.service.engine.independant.objects.StorageService;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private ServletContext servletContext;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    	Long maxFileSize =  Long.parseLong(servletContext.getInitParameter("http.multipart.max-file-size"));
    	Long maxRequestSize = Long.parseLong(servletContext.getInitParameter("http.multipart.max-request-size"));
    	Integer fileSizeThreshold = Integer.parseInt(servletContext.getInitParameter("http.multipart.file-size-threshold"));
    	
        // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                		maxFileSize, maxRequestSize, fileSizeThreshold);

        registration.setMultipartConfig(multipartConfigElement);

    }
	
	
	
	
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("Enter in onStartup()");
		this.servletContext = servletContext;
		setInitParameters();
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}
	
	private void setInitParameters() throws ServletException {
		try {
			Properties initParams = new Properties();
			initParams.load(new ClassPathResource("application.properties").getInputStream());
			initParams.stringPropertyNames().stream().forEach(propertyName -> servletContext.setInitParameter(propertyName, initParams.getProperty(propertyName)));
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

}
