package com.datastax.tutorials.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiDocumentationConfig implements WebMvcConfigurer {
    
    @Bean
    public OpenAPI openApiSpec() {
        return new OpenAPI().addServersItem(new Server().url("/")).info(
                new Info()
                    .title("DevWorkshop :: ECommerce Backend")
                    .version("1.0")
                    .description(""
                            + "Implementation of ECommerce application with "
                            + "Spring WebMVC and storage in DataStax AstraDB")
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0")
                    .url("http://springdoc.org")));
    }
    
    /**
     * By overriding `addResourceHandlers` from {@link WebMvcConfigurer}, 
     * we tell SpringMVC not to use Thymeleaf to access Swagger UI
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
}
