package io.ispacc.orion.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.ispacc.orion")
public class OrionAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrionAuthApplication.class, args);
    }
}

