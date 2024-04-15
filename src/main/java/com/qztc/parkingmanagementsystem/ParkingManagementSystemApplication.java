package com.qztc.parkingmanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.qztc.parkingmanagementsystem.mapper")
@ServletComponentScan("com.qztc.parkingmanagementsystem.filter")
//开启定时任务
@EnableScheduling
public class ParkingManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingManagementSystemApplication.class, args);
    }

}
