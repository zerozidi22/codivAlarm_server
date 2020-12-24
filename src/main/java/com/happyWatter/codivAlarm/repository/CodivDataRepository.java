package com.happyWatter.codivAlarm.repository;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodivDataRepository extends JpaRepository<ApiCodivData, Long> {

    ApiCodivData findByCreateDt(String createDt);


    List<ApiCodivData> findByCreateDtInOrderByCreateDtDesc(List<String> createDt);
}
