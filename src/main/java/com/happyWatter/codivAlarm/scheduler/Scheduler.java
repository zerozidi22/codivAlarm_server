package com.happyWatter.codivAlarm.scheduler;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.service.ApiService;
import com.happyWatter.codivAlarm.service.DataService;
import com.happyWatter.codivAlarm.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private ApiService apiService;

    @Autowired
    private SendService sendService;

    @Autowired
    private DataService dataService;

    //데이터 수집
//    @Scheduled(cron = "0 * 9 * * ?")
//    @Scheduled(cron = "* * * * * *")
    public void cronJobForDataCallFromApiServcer() throws Exception {

        List<ApiCodivData> data = apiService.getCodivDataFromServer();
        dataService.saveData(data);

    }

    //데이터 수집
//    @Scheduled(cron = "0 0 10 * * ?")
//    @Scheduled(cron = "* * * * * *")
    public void cronJobForSendToFcm() throws Exception {

        Long decideCnt = apiService.getCodivDate();

        String title = "오늘의 확진자";
        String body =  decideCnt + "명 입니다.";
        sendService.sendToFcm(title, body);


    }
}