package com.fsd.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(scanBasePackages = {"com.fsd.project"}) 
@EnableJpaRepositories("com.fsd.project.repository")
@EntityScan("com.fsd.project.entity")
public class ProjectmanagerApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagerApplication.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ProjectmanagerApplication.class);
	    }	
	 
	 @Bean
	 public CorsFilter corsFilter() {
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     CorsConfiguration config = new CorsConfiguration();
	     config.addAllowedOrigin("*");
	     config.addAllowedHeader("*");
	     config.addAllowedMethod("OPTIONS");
	     config.addAllowedMethod("HEAD");
	     config.addAllowedMethod("GET");
	     config.addAllowedMethod("PUT");
	     config.addAllowedMethod("POST");
	     config.addAllowedMethod("DELETE");
	     config.addAllowedMethod("PATCH");
	     source.registerCorsConfiguration("/**", config);
	     return new CorsFilter(source);
	 }
}
