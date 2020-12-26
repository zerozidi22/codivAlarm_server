package com.happyWatter.codivAlarm.scheduler;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.service.ApiService;
import com.happyWatter.codivAlarm.service.DataService;
import com.happyWatter.codivAlarm.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    private ApiService apiService;

    @Autowired
    private SendService sendService;

    @Autowired
    private DataService dataService;

    //데이터 수집
//    @Scheduled(cron = " 0 * 9 * * *")
    @Scheduled(cron = " 0 * 21 * * *")
    public void cronJobForDataCallFromApiServcer() throws Exception {

        List<ApiCodivData> data = apiService.getCodivDataFromServer();
        ApiCodivData rst = dataService.saveData(data);

        if(rst != null){
            ApiCodivData today =  dataService.getDataByCreateDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            ApiCodivData yesterDay =  dataService.getDataByCreateDt(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

            dataService.updateCreateDt(today.getDecideCnt() - yesterDay.getDecideCnt());
        }
    }

    //푸시 발송
//    @Scheduled(cron = " 0 0 10 * * *")
    @Scheduled(cron = " 0 0 22 * * *")
    public void cronJobForSendToFcm() throws Exception {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        ApiCodivData apiCodivData = apiService.getCodivDate(now);

        String title = "오늘의 확진자( " + apiCodivData.getCreateDt() +  "일 기준)";
        String body =  apiCodivData.getDecideGapCnt() + "명 입니다.";
        sendService.sendToFcm(title, body);

    }
}