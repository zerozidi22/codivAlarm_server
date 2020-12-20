package com.happyWatter.codivAlarm.service;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.repository.CodivDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    public CodivDataRepository codivDataRepository;

    public void saveData(List<ApiCodivData> data) {
        for(ApiCodivData dt : data){
            ApiCodivData rstDt = codivDataRepository.findByCreateDt(dt.getCreateDt());
            if(rstDt == null){
                codivDataRepository.save(dt);
            }
        }
    }


}
