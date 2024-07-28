package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.domain.bo.CommBo;
import com.qztc.parkingmanagementsystem.domain.dto.CommParkDto;
import com.qztc.parkingmanagementsystem.domain.dto.ParkShareDto;
import com.qztc.parkingmanagementsystem.domain.po.Point;
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
import jakarta.validation.Valid;
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
    public ResultVo park(@RequestBody @Valid CommParkDto commParkDto) {

        Point point = new Point(commParkDto.getX(), commParkDto.getY());

        List<BCommPo> nearestComm = commService.findNearestComm(point, 5);
        if (nearestComm == null || nearestComm.isEmpty()) {
            return ResultVo.error("附近没有小区");
        }
        CommParkVo commParkVo = new CommParkVo();
        commParkVo.setCommVoList(new ArrayList<>());
        for (BCommPo bCommPo : nearestComm) {
            List<BPark> parkByCommId = parkService.findParkByCommId(bCommPo.getCommId(), commParkDto.getParkTime());
            CommVo commVo = CommBo.commToCommVo(bCommPo, parkByCommId);
            commParkVo.getCommVoList().add(commVo);
        }
        commParkVo.setCount(nearestComm.size());

        return ResultVo.success(commParkVo);
    }

    @PostMapping("/ParkShare")
    @Operation(summary = "我要共享停车位")
    public ResultVo parkShare(@RequestBody @Valid ParkShareDto parkShareDto) {
        return parkService.parkShare(parkShareDto);
    }

    @GetMapping("/getComm")
    @Operation(summary = "获取附近小区")
    public ResultVo getComm(@RequestParam String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        List<BCommPo> nearestComm = commService.selectCommByKey(key);
        return ResultVo.success(nearestComm);
    }

    @GetMapping("/getPark")
    @Operation(summary = "获取小区所有停车位")
    public ResultVo getPark(@RequestParam Long commId, @RequestParam Integer hour) {
        List<BPark> parkByCommId = parkService.findParkByCommId(commId, hour);
        return ResultVo.success(parkByCommId);
    }

    @PostMapping("/getParkByparkId")
    @Operation(summary = "根据停车位id查找停车位")
    public ResultVo getParkByparkId(@RequestParam Long parkId) {
        BPark parkById = parkService.findParkById(parkId);
        return ResultVo.success(parkById);
    }

}
