package com.happyWatter.codivAlarm.controller;


import com.happyWatter.codivAlarm.entity.User;
import com.happyWatter.codivAlarm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    public TokenService tokenService;

    @PostMapping("")
    public void createToken(@RequestBody User token){
        tokenService.createToken(token.getToken());
    }




}
