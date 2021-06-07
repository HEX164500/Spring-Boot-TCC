package com.sigen.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@EnableWebMvc
@SpringBootApplication
public class ApiApplication{

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		DispatcherServlet dispatcherServlet = (DispatcherServlet) context.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}
}
