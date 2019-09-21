package cn.itcast.up.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResult<T> {
    private Integer code;
    private String msg;
    // 不知道啥类型, 类上声明了什么类型就是什么类型
    private T data;
}
