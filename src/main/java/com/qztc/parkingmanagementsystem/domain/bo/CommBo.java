package com.qztc.parkingmanagementsystem.domain.bo;

import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.domain.vo.CommVo;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author congyijiu
 * @create 2024-05-10-09:48
 */
public class CommBo {

    public static CommVo commToCommVo(BCommPo bCommPo, List<BPark> parkList) {
        CommVo commVo = new CommVo();
        BeanUtils.copyProperties(bCommPo, commVo);
        commVo.setParkList(parkList);
        commVo.setCount(parkList.size());
        //todo 最高价格,数据不全，暂时不做
        //todo 最低价格,数据不全，暂时不做
        //todo 最低5个价格平均数,数据不全，暂时不做
        return commVo;
    }
}
