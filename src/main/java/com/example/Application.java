package com.example;

import com.example.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) // special for storage
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
