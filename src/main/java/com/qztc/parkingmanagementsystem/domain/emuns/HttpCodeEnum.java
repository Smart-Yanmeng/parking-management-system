package com.qztc.parkingmanagementsystem.domain.emuns;

/**
 * @author congyijiu
 * @create 2024-04-01-11:39
 */
public enum HttpCodeEnum {
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    NOT_FOUND(404, "not found"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden");

    private Integer code;

    private String msg;



    HttpCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }




}
