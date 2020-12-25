package com.happyWatter.codivAlarm.controller;


import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.entity.User;
import com.happyWatter.codivAlarm.service.ApiService;
import com.happyWatter.codivAlarm.service.SendService;
import com.happyWatter.codivAlarm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    public TokenService tokenService;

    @Autowired
    public ApiService apiService;

    @Autowired
    public SendService sendService;

    @PostMapping("")
    public void createToken(@RequestBody User token){
        tokenService.createToken(token.getToken());


        ApiCodivData apiCodivData = apiService.getCodivDate();

        String title = "오늘의 확진자( " + apiCodivData.getCreateDt() +  "일 기준)";
        String body =  apiCodivData.getDecideGapCnt() + "명 입니다.";
        sendService.sendToFcmToOnePerson(title, body, token.getToken());

    }


}
