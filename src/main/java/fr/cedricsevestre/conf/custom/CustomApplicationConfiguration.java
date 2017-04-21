package fr.cedricsevestre.conf.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class CustomApplicationConfiguration extends WebMvcConfigurerAdapter {
	
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		registry.addInterceptor(interceptor);

		
		
		
		//ServerNameInterceptor serverNameInterceptor = new ServerNameInterceptor();
		//registry.addInterceptor(serverNameInterceptor);
	}
	

}
