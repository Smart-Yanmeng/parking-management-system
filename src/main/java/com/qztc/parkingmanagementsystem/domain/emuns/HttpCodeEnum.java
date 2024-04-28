package com.qztc.parkingmanagementsystem.domain.emuns;

import lombok.Getter;

/**
 * @author congyijiu
 * @create 2024-04-01-11:39
 */
@Getter
public enum HttpCodeEnum {
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    NOT_FOUND(404, "not found"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden");

    private final Integer code;

    private final String msg;

    HttpCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
