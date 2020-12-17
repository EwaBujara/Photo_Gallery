package pl.gallery;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"pl.gallery"})
@EnableJpaRepositories(basePackages="pl.gallery.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="pl.gallery.entities")
public class PhotoGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoGalleryApplication.class, args);
	}
	
	  @Bean(name="localeResolver")
	    public LocaleContextResolver getLocaleContextResolver() {
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(new Locale("pl","PL"));
	        return localeResolver; }

	    @Bean
	    public Validator validator() {
	        return new LocalValidatorFactoryBean();
	    }
	
	 @Bean
	    public MessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasenames("ValidationMessages");
	        return messageSource;
	    }

}
