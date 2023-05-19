package io.ispacc.orion.admin.controller;

import io.ispacc.orion.admin.model.UmsMember;
import io.ispacc.orion.admin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("hello")
    public String hello() {
        return "Test Web";
    }


    @GetMapping("test1")
    public List<UmsMember> test1() {
        return testService.test1();
    }
}
