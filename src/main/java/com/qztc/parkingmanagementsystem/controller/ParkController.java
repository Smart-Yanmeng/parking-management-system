package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.domain.dto.CommParkDto;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.domain.vo.CommParkVo;
import com.qztc.parkingmanagementsystem.domain.vo.CommVo;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.service.ICommService;
import com.qztc.parkingmanagementsystem.service.IParkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-28-20:34
 */

@RestController
@RequestMapping("/park")
@Tag(name = "停车场管理")
public class ParkController {

    @Resource
    private IParkService parkService;

    @Resource
    private ICommService commService;

    @PostMapping("/park")
    @Operation(summary = "我要停车")
    public ResultVo park(@RequestBody CommParkDto commParkDto) {
        if (commParkDto == null) {
            return ResultVo.error("参数错误");
        }
        if (commParkDto.getPoint() == null || commParkDto.getParkTime() == null) {
            return ResultVo.error("参数错误");
        }


        List<BCommPo> nearestComm = commService.findNearestComm(commParkDto.getPoint(), 5);
        if (nearestComm == null || nearestComm.isEmpty()) {
            return ResultVo.error("附近没有小区");
        }
        CommParkVo commParkVo = new CommParkVo();
        commParkVo.setCommVoList(new ArrayList<>());
        for (BCommPo bCommPo : nearestComm) {
            List<BPark> parkByCommId = parkService.findParkByCommId(bCommPo.getCommId(), commParkDto.getParkTime());
            CommVo commVo = new CommVo();
            BeanUtils.copyProperties(bCommPo, commVo);
            commVo.setParkList(parkByCommId);
            commVo.setCount(parkByCommId.size());
            //todo 最高价格,数据不全，暂时不做
            //todo 最低价格,数据不全，暂时不做
            //todo 最低5个价格平均数,数据不全，暂时不做
            commParkVo.getCommVoList().add(commVo);
        }
        commParkVo.setCount(nearestComm.size());

        return ResultVo.success(commParkVo);
    }
}
