package com.happyWatter.codivAlarm.service;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.repository.CodivDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataService {

    @Autowired
    public CodivDataRepository codivDataRepository;

    public void saveData(List<ApiCodivData> data, Long gapCnt) {
        for(ApiCodivData dt : data){
            ApiCodivData rstDt = codivDataRepository.findByCreateDt(dt.getCreateDt());
            if(rstDt == null){
                dt.setDecideGapCnt(gapCnt);
                codivDataRepository.save(dt);
            }
        }
    }

    public ApiCodivData getData(){
       return codivDataRepository.findByCreateDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }


}
