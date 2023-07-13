package io.ispacc.orion.admin.core.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
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
    private final static String[] URL_CONSTANT = {"favicon.ico", "/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html/**", "/doc.html"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //为所有请求设置用户信息线程变量
        registry.addInterceptor(new SaInterceptor(h -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(URL_CONSTANT);
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
