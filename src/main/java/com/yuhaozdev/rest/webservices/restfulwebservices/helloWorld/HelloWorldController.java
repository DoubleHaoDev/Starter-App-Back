package com.yuhaozdev.rest.webservices.restfulwebservices.helloWorld;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {
    //GET


    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    //hello-world bean
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello Worldd");
    }


    @GetMapping(path = "/hello-world/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
//        throw new RuntimeException("Something went wrong");
        return new HelloWorldBean(String.format("Hello Worldd, %s", name));
    }
}
