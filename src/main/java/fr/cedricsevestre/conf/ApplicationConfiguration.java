package fr.cedricsevestre.conf;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.taglib.Head;

@Configuration
@ComponentScan(basePackages = "fr.cedricsevestre")
@Import({ SecurityConfiguration.class })
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableSpringDataWebSupport
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver res = new InternalResourceViewResolver();
		res.setViewClass(JstlView.class);
		res.setPrefix("/WEB-INF/");
		res.setSuffix(".jsp");
		return res;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/test/**").addResourceLocations("/WEB-INF/test/");
		registry.addResourceHandler("/style/**").addResourceLocations("/WEB-INF/style/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/image/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
	}

	@Bean
	public MessageSource messageSource() {
		SerializableResourceBundleMessageSource messageSource = new SerializableResourceBundleMessageSource();
		messageSource.setBasenames("/WEB-INF/i18n/label_back", "/WEB-INF/i18n/label_front");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("language");
		resolver.setCookieMaxAge(60*60*24*365);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);

		//ServerNameInterceptor serverNameInterceptor = new ServerNameInterceptor();
		//registry.addInterceptor(serverNameInterceptor);
	}
	

	@Bean
	public ServerNameExtractingWebArgumentResolver serverNameExtractingWebArgumentResolver() {
		return new ServerNameExtractingWebArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(serverNameExtractingWebArgumentResolver());
	}
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // the list is empty, so we just add our converter
        converters.add(jsonConverter());
    }

    @Bean
    public HttpMessageConverter<Object> jsonConverter() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                .json()
                .serializerByType(String.class, new SanitizedStringSerializer())
                .build();
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
	
	
	
	
	
	
	
	

	@Component
	public static class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

		@Autowired
		private InitialisationBase initialisationBase;

		public StartupListener() {
			// TODO Auto-generated constructor stub
		}

		private static Integer count = 0;

		@Override
		public void onApplicationEvent(final ContextRefreshedEvent event) {
			count += 1;
			Logger logger = Logger.getLogger(ApplicationConfiguration.class);
			logger.info("Passage n°" + count);
			if (count == 1) {
				logger.info("INIT");
				try {
					initialisationBase.run();

				} catch (ServiceException e) {

					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
