package com.MVP;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MvpHotelMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvpHotelMgmtApplication.class, args);
	}

	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Bean public Docket productApi() { return new
			  Docket(DocumentationType.SWAGGER_2).select().
			  apis((java.util.function.Predicate<RequestHandler>)
			  RequestHandlerSelectors.basePackage("com.MVP")).build();
			  
	}
}