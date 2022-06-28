package com.ty.springboothospitalmgmt;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootHospitalmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHospitalmgmtApplication.class, args);
	}

	Collection<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
	Contact contact = new Contact("Test Yantra", "https://www.testyantra.com/", "intiyaz.a@ty.com");

	ApiInfo apiInfo = new ApiInfo("Hospital Management System", "Hospital Management", "Snapshot-0.0.1",
			"https://www.testyantra.com/", contact, "TY", "https://www.testyantra.com/", vendorExtensions);

	@Bean
	public Docket myDocket() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ty.springboothospitalmgmt")).build().apiInfo(apiInfo);
	}
}
