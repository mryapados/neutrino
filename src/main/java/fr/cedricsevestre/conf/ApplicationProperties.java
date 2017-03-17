package fr.cedricsevestre.conf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	
	@Resource
	private Environment environment;

	private String webInfFolder;
	
	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPassword;
	
	private String applicationFolder;
	private String pathBundleLabelsBack;
	private String pathBundleLabelsFront;

	private Long maxFileSize;
	private Long maxRequestSize;
	private Integer fileSizeThreshold;
	private String uploadDir;
	
	@PostConstruct
	public void init() {
		jdbcUrl = environment.getProperty("jdbc.url");
		jdbcUser = environment.getProperty("jdbc.user");
		jdbcPassword = environment.getProperty("jdbc.password");
		
		applicationFolder = environment.getProperty("application.folder");		
		pathBundleLabelsBack  = environment.getProperty("path.bundle.labels.back");
		pathBundleLabelsFront  = environment.getProperty("path.bundle.labels.front");
		
		maxFileSize =  Long.parseLong(environment.getProperty("http.multipart.max-file-size"));
		maxRequestSize = Long.parseLong(environment.getProperty("http.multipart.max-request-size"));
		fileSizeThreshold = Integer.parseInt(environment.getProperty("http.multipart.file-size-threshold"));
		uploadDir  = environment.getProperty("upload.dir");	
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public String getJdbcUser() {
		return jdbcUser;
	}
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	public String getApplicationFolder() {
		return applicationFolder;
	}
	public String getPathBundleLabelsBack() {
		return pathBundleLabelsBack;
	}
	public String getPathBundleLabelsFront() {
		return pathBundleLabelsFront;
	}
	public Long getMaxFileSize() {
		return maxFileSize;
	}
	public Long getMaxRequestSize() {
		return maxRequestSize;
	}
	public Integer getFileSizeThreshold() {
		return fileSizeThreshold;
	}
	public String getUploadDir() {
		return uploadDir;
	}

}
