package com.poscodx.mysite.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.poscodx.mysite.config.web.MvcConfig;
import com.poscodx.mysite.event.ApplicationContextEventListener;

@Configuration
@EnableAspectJAutoProxy
// context:annotation-config (spring-servlet.xml)
@ComponentScan({ "com.poscodx.mysite.controller", "com.poscodx.mysite.exception" })
@Import({ MvcConfig.class , MessageSource.class })
public class WebConfig {

	// Application Context Event Listener (spring-servlet.xml)
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
}
