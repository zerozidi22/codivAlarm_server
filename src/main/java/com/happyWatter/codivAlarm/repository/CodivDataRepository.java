package com.happyWatter.codivAlarm.repository;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodivDataRepository extends JpaRepository<ApiCodivData, Long> {


    ApiCodivData findByCreateDt(String createDt);

}
