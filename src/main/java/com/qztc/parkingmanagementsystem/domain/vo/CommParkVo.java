package com.qztc.parkingmanagementsystem.domain.vo;

import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import lombok.Data;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-28-20:35
 */

@Data
public class CommParkVo {
    private List<CommVo> commVoList;

    private Integer count;

}
