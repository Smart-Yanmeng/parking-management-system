package com.qztc.parkingmanagementsystem.domain.po;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2024-04-15-16:05
 */

@Data
public class BCommPo {

        private Long commId;

        private String commName;

        private String addr;

        private double geoX;

        private double geoY;


}
