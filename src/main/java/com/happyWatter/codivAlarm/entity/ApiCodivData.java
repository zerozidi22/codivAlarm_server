package com.happyWatter.codivAlarm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ApiCodivData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    public String createDt;

    @Column(nullable = false, columnDefinition = "BIGINT")
    public Long decideCnt;

    @Column(nullable = true, columnDefinition = "BIGINT")
    public Long decideGapCnt;

}
