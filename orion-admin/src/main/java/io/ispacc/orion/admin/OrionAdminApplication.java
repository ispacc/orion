package io.ispacc.orion.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("io.ispacc.orion.admin.module.*.mapper")
public class OrionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrionAdminApplication.class, args);
    }

}
