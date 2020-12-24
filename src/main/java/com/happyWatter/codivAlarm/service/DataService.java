package com.happyWatter.codivAlarm.service;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.repository.CodivDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    public CodivDataRepository codivDataRepository;

    public ApiCodivData saveData(List<ApiCodivData> data) {
        for(ApiCodivData dt : data){
            ApiCodivData rstDt = codivDataRepository.findByCreateDt(dt.getCreateDt());
            if(rstDt == null){
                return codivDataRepository.save(dt);
            }
        }
        return null;
    }

    public ApiCodivData getData(){

        List<String> condition = new ArrayList<>();
        condition.add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        condition.add(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
       return codivDataRepository.findByCreateDtInOrderByCreateDtDesc(condition).get(0);
    }

    public ApiCodivData getDataByCreateDt(String date){
        return codivDataRepository.findByCreateDt(date);
    }

    public void updateCreateDt(Long gap){
        ApiCodivData dt = codivDataRepository.findByCreateDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        dt.setDecideGapCnt(gap);

        codivDataRepository.save(dt);

    }


}
