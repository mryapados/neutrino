package fr.cedricsevestre.conf.core;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import fr.cedricsevestre.conf.ApplicationConfiguration;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB TODO in properties
	
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

        // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        
//        new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
//        		applicationProperties.getMaxFileSize(), 
//        		applicationProperties.getMaxRequestSize(), 
//        		applicationProperties.getFileSizeThreshold());
        
        
        registration.setMultipartConfig(multipartConfigElement);

    }
	
	
	
	
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("Enter in onStartup()");
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}

}
