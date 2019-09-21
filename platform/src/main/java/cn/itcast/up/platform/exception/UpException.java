package cn.itcast.up.platform.exception;

import lombok.Data;

/**
 * 当工程中出现了无法处理的情况, 抛出提示
 */
@Data
public class UpException extends RuntimeException {
    // 应该包含, code, msg
    private Integer code;
    private String msg;

    public UpException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
