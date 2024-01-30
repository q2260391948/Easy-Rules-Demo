package com.xiaozhang.actruledemo.demos.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author:22603
 * @Date:2024/1/22 11:17
 */
@Data
@ToString
@NoArgsConstructor
public class RuleResult <T> implements Serializable {
    private int code;
    private String message;
    private T data;
    public RuleResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public RuleResult(int code, T data) {
        this(code, data, "");
    }

    public RuleResult(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

}
