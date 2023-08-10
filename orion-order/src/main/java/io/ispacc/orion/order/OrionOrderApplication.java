package io.ispacc.orion.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.ispacc.orion")
public class OrionOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrionOrderApplication.class, args);
    }
}
