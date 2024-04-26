package com.qztc.parkingmanagementsystem.domain.vo;

import com.qztc.parkingmanagementsystem.domain.emuns.HttpCodeEnum;
import lombok.Data;

@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(HttpCodeEnum.SUCCESS.getCode());
        resultVo.setMsg(HttpCodeEnum.SUCCESS.getMsg());
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo<?> error() {
        ResultVo<?> resultVo = new ResultVo<>();
        resultVo.setCode(HttpCodeEnum.ERROR.getCode());
        resultVo.setMsg(HttpCodeEnum.ERROR.getMsg());
        return resultVo;
    }

    public static ResultVo<?> error(String msg) {
        ResultVo<?> resultVo = new ResultVo<>();
        resultVo.setCode(HttpCodeEnum.ERROR.getCode());
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo<?> error(Integer code, String msg) {
        ResultVo<?> resultVo = new ResultVo<>();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo<?> error(HttpCodeEnum httpCodeEnum) {
        ResultVo<?> resultVo = new ResultVo<>();
        resultVo.setCode(httpCodeEnum.getCode());
        resultVo.setMsg(httpCodeEnum.getMsg());
        return resultVo;
    }
}
