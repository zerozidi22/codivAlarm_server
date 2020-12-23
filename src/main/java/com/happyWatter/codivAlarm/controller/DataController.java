package com.happyWatter.codivAlarm.controller;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("")
    public ApiCodivData getCodivData(){
        return dataService.getData();
    }
}
