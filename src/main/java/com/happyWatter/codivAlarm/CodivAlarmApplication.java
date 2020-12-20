package com.happyWatter.codivAlarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CodivAlarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodivAlarmApplication.class, args);
	}

}
