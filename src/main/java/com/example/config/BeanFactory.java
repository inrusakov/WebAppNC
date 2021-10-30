package com.example.config;

import com.example.model.Traveling.JourneyRequestForm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanFactory {

    @Scope("prototype")
    @Bean("journeyRequestForm")
    public JourneyRequestForm journeyRequestForm(){
        return new JourneyRequestForm();
    }
}
