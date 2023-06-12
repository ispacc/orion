package io.ispacc.orion.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching
@MapperScan("io.ispacc.orion.admin")
public class OrionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrionAdminApplication.class, args);
    }

}
