package io.ispacc.orion.admin.core.config;

import io.ispacc.orion.admin.core.interceptor.LoginUserInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置类
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-14 09:07
 */
@EnableAsync
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginUserInterceptor loginUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //为所有请求设置用户信息线程变量
        registry.addInterceptor(loginUserInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/register", "/admin/login", "favicon.ico", "/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html/**", "/doc.html")
                .order(1);
    }

    //跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
