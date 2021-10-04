package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/blogPage").setViewName("blogPage");
        registry.addViewController("/post").setViewName("post");
        registry.addViewController("/postObserver").setViewName("postObserver");
        registry.addViewController("/editPost").setViewName("editPost");
        registry.addViewController("/profile").setViewName("profile");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/css/**").addResourceLocations("classpath:/static/styles/css/");
    }
}
