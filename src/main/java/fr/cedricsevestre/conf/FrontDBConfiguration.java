package fr.cedricsevestre.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@PropertySource(value = "classpath:config.properties", name = "config")
@EnableJpaRepositories(
		basePackages="fr.cedricsevestre.dao.front", 
		entityManagerFactoryRef="frontEntityManagerFactory", 
		transactionManagerRef = "frontTransactionManager")
public class FrontDBConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	private Environment environment;

	@Bean(name = "frontDataSource")
	@Primary
	public DataSource frontDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(environment.getProperty("jdbc.front.url"));
		dataSource.setUsername(environment.getProperty("jdbc.front.user"));
		dataSource.setPassword(environment.getProperty("jdbc.front.password"));
		return dataSource;
	}

	@Bean(name = "frontEntityManagerFactory")
	@Primary
	public EntityManagerFactory frontEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setPersistenceUnitName("persistenceUnit");
		lcemfb.setDataSource(frontDataSource());
		lcemfb.setJpaDialect(new HibernateJpaDialect());
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lcemfb.setPackagesToScan("fr.cedricsevestre.entity.front");

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		lcemfb.setJpaProperties(properties);
		lcemfb.afterPropertiesSet();

		return lcemfb.getObject();
	}
	
	@Bean
	@Primary
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jpaVendorAdapter;
	}

	@Bean
	@Primary
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean(name = "frontTransactionManager")
	@Primary
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(frontEntityManagerFactory());
		return jpaTransactionManager;
	}
	


}
