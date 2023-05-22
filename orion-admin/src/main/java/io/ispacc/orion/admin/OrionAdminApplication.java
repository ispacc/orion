package io.ispacc.orion.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.ispacc.orion.admin.mapper")
@MapperScan("io.ispacc.orion.admin.dao")
public class OrionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrionAdminApplication.class, args);
    }

}
