package io.ispacc.orion.admin.controller;

import io.ispacc.orion.admin.model.UmsMember;
import io.ispacc.orion.admin.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("hello")
    public String hello() {
        return """
                {
                    你好啊
                    我不好
                }
                """;
    }

    @GetMapping("test1")
    public List<UmsMember> test1() {
        log.info("------");
        return testService.test1();
    }
}
