package org.example.trainingspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imageFolder = System.getProperty("user.dir") + "/images/";
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + imageFolder);
    }

}
