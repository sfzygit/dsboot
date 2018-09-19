package com.example.dsboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Api(description = "测试api")
public class TestController {

    @RequestMapping(value = "/serviceA",method = RequestMethod.GET)
    @ApiOperation(value = "service A")
    public Map<String,String> serviceA(
            @RequestParam(required = false,defaultValue = "default value") String param
    ) {
        Map<String,String> result = new HashMap<>();
        result.put("value",param);
        return result;
    }
}
