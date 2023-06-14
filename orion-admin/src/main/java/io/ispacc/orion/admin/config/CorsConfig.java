package io.ispacc.orion.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://flysean.love") // 设置允许跨域请求的源地址
                .allowedMethods("*") // 设置允许的HTTP方法
                .allowedHeaders("*") // 设置允许的HTTP头部
                .allowCredentials(true); // 允许发送身份验证信息
    }
}
