package com.dexsys.tgbot.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        Contact contact = new Contact("Kirill Tikhonov", "https://birthdaybot-dexsys.herokuapp.com", "akirilltikonov@gmail.com");
        ApiInfo apiInfo = new ApiInfo("BirthdayReminder tg bot",
                "REST controller to BirthdayReminder tg bot",
                "0.0.1",
                null,
                contact,
                null,
                null,
                Collections.singletonList(new StringVendorExtension("Kirill", "Tikhonov")));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dexsys.tgbot.app.controllers"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo);
    }
}
