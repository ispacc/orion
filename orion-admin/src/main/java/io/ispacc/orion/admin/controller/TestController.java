package io.ispacc.orion.admin.controller;

import io.ispacc.orion.admin.entity.UmsAdmin;
import io.ispacc.orion.admin.entity.UmsMember;
import io.ispacc.orion.admin.service.TestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "测试接口")
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

    @GetMapping("test2")
    public List<UmsAdmin> test2() {
        return testService.test2();
    }
}
