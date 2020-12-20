package com.happyWatter.codivAlarm.controller;


import com.happyWatter.codivAlarm.entity.User;
import com.happyWatter.codivAlarm.scheduler.Scheduler;
import com.happyWatter.codivAlarm.service.SendService;
import com.happyWatter.codivAlarm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {


    @Autowired
    public TokenService tokenService;

    @Autowired
    private SendService sendService;

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/test")
    public void test() throws Exception {
        scheduler.cronJobForSendToFcm();
    }

    @PostMapping("/test1")
    public void test1() throws Exception {
        scheduler.cronJobForDataCallFromApiServcer();
    }

    @PostMapping("")
    public void createToken(@RequestBody User token){

        tokenService.createToken(token.getToken());
    }


}
