package io.ispacc.orion.order.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.ispacc.orion.order.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @DeleteMapping("/del")
    @SaIgnore
    public Long del() {
        return testService.method1();
    }
}
