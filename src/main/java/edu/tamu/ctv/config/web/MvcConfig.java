package edu.tamu.ctv.config.web;

import edu.tamu.ctv.config.data.DataConfig;
import edu.tamu.ctv.config.security.SecurityConfig;
import edu.tamu.ctv.controller.converters.UserToProjectUsersConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "edu.tamu.ctv", "edu.tamu.ctv.service" })
@Import({ SecurityConfig.class, DataConfig.class })
public class MvcConfig extends WebMvcConfigurerAdapter
{

	@Autowired
	UserToProjectUsersConverter userToProjectUserConverter;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver()
	{
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void addFormatters(FormatterRegistry registry)
	{
		registry.addConverter(userToProjectUserConverter);
	}

	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver()
	{
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(100000000);
		return cmr;
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource()
	{
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messages");
		resource.setDefaultEncoding("UTF-8");
		//TODO: Change
		resource.setUseCodeAsDefaultMessage(true);
		return resource;
	}
	
/*	@Bean(name = "localeResolver")
	public SessionLocaleResolver getSessionLocaleResolver()
	{
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(StringUtils.parseLocaleString(""));
		return localeResolver;
	}
	
	@Bean(name = "localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor()
	{
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}*/

}