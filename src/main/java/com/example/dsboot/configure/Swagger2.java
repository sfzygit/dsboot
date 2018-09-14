package com.example.dsboot.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenPar.name("token").description("用户token").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.dsboot.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameters);
    }
}
