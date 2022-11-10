package com.dailycode.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class FallBackMethodController {

    // @GetMapping("/userServiceFallBack")
    // @HystrixCommand(fallbackMethod = "userServiceFallBackMethod_fallback",
    //     commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", 
    //         value = "4000")
    // })
    public String userServiceFallBackMethod() {

        return "User Service is taking longer than expected."+
            " Please try again later.";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {

        return "Department Service is taking longer than expected."+
            " Please try again later.";
    }
}