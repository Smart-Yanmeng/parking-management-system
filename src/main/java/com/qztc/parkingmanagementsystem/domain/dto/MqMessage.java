package com.qztc.parkingmanagementsystem.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MqMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    private String content;
    private long timestamp;
}
