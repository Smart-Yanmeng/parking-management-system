package com.qztc.parkingmanagementsystem.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public static ResultVo success() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("success");

        return resultVo;
    }

    public static ResultVo success(Object data) {
        ResultVo resultVo = success();
        resultVo.setData(data);

        return resultVo;
    }

    public static ResultVo error() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(500);
        resultVo.setMsg("error");

        return resultVo;
    }

    public static ResultVo error(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(500);
        resultVo.setMsg(msg);

        return resultVo;
    }
}
