package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/map").setViewName("map");
        registry.addViewController("/routeObserver").setViewName("routeObserver");
        registry.addViewController("/editRoute").setViewName("editRoute");
        registry.addViewController("/").setViewName("map");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/blogPage").setViewName("blogPage");
        registry.addViewController("/blog/*").setViewName("blogPage");
        registry.addViewController("/post").setViewName("post");
        registry.addViewController("/postObserver").setViewName("postObserver");
        registry.addViewController("/editPost").setViewName("editPost");
        registry.addViewController("/profile").setViewName("profile");

        registry.addViewController("/comment").setViewName("comment");
        registry.addViewController("/likeComment").setViewName("likeComment");
        registry.addViewController("/addComment").setViewName("addComment");
        registry.addViewController("/editComment").setViewName("editComment");
        registry.addViewController("/reply").setViewName("reply");
        registry.addViewController("/replyToComment").setViewName("replyToComment");
        registry.addViewController("/userList").setViewName("userList");
        //registry.addViewController("/blogList").setViewName("blogList");

        //for rest blog part
        //registry.addViewController("/index").setViewName("index");
        //registry.addViewController("/blog/blogList").setViewName("blog/blogList");
       // registry.addViewController("/blog/blogPage/*").setViewName("blog/blogPage");
        //registry.addViewController("/blog/postObserver").setViewName("/blogpage/*/post/*");
        //registry.addViewController("/blog/addPost").setViewName("/blogpage/*/addpost");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/css/**").addResourceLocations("classpath:/static/styles/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}
