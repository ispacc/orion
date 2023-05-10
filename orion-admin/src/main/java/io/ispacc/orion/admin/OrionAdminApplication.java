package io.ispacc.orion.admin;

import jdk.jpackage.internal.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrionAdminApplication.class, args);
        Log.info("hellow ");
    }

}
