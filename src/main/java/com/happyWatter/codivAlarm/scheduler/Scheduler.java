package com.happyWatter.codivAlarm.scheduler;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.service.ApiService;
import com.happyWatter.codivAlarm.service.DataService;
import com.happyWatter.codivAlarm.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Scheduled(cron = "0 */1 22 * * ?")
    public void cronJobForDataCallFromApiServcer() throws Exception {
        System.out.println("data" + LocalDateTime.now());
        List<ApiCodivData> data = apiService.getCodivDataFromServer();
        Long gapCnt = data.get(0).getDecideCnt() - data.get(1).getDecideCnt();
        dataService.saveData(data, gapCnt);

    }

    //푸시 발송
//    @Scheduled(cron = "0 0 10 * * ?")
    @Scheduled(cron = "0 0 23 * * ?")
    public void cronJobForSendToFcm() throws Exception {
        System.out.println("fcm" + LocalDateTime.now());
        Long decideCnt = apiService.getCodivDate();

        String title = "오늘의 확진자( " + LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) +  "일 기준)";
        String body =  decideCnt + "명 입니다.";
        sendService.sendToFcm(title, body);

    }
}