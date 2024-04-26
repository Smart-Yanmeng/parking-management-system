package com.qztc.parkingmanagementsystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

@SpringBootTest
class ParkingManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void jsontest() {
        String json = "[\n" +
                "{\"start\": \"09:00\", \"end\": \"12:00\"},\n" +
                "{\"start\": \"13:00\", \"end\": \"17:00\"}\n" +
                "]";
        JSONArray jsonArray = JSON.parseArray(json);
    }

}
