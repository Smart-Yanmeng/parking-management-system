package com.qztc.parkingmanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qztc.parkingmanagementsystem.mapper")
public class ParkingManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingManagementSystemApplication.class, args);
    }

}
