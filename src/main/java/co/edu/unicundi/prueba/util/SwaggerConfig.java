package co.edu.unicundi.prueba.util;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact("UDEC","https://www.ucundinamarca.edu.co",
			"info@unicundi.edu.co");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Cristians Documentacion","Documentacion",
			"1.0","PREMIUM",DEFAULT_CONTACT,"apache 2.0","https://apache.org/licenses/LICENSE-2.0",
			new ArrayList<>());
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO).select()
	            .paths(PathSelectors.any())
	            .paths(Predicates.not(PathSelectors.regex("/error.*")))
	            .build();
		
	}

}
