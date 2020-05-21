package com.techment.SwaggerDemo;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;


@SpringBootApplication
@EnableSwagger2
public class SwaggerDemoApplication {

//	@Bean
//	public Docket docket() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.techment.SwaggerDemo")).paths(any()).build()
//				.apiInfo(new ApiInfo("Basic Demo Project",
//						"API's for the Address service", "2.0", null,
//						new Contact("LinkedIn Learning","https://www.linkedin.com/learning", ""),
//						null, null, new ArrayList()));
//	}
	

	
	
	//@TestPropertySource(locations = "classpath:application.properties")
	
	public static void main(String[] args) {
		SpringApplication.run(SwaggerDemoApplication.class, args);
		
		
	}

}
