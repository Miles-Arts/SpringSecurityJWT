package com.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Word Not Secureted";
    }

    @GetMapping("/helloSecured")
    public String helloSecured()
    {
        return "Hello Word Yes Secureted";
    }

    @PatchMapping("/createUser")
    public ResponseEntity<?> createUser()
    {

    }

}
