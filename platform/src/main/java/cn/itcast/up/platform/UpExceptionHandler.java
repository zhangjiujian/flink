package cn.itcast.up.platform;

import cn.itcast.up.platform.entity.HttpResult;
import cn.itcast.up.platform.exception.UpException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class UpExceptionHandler {

    @ExceptionHandler(UpException.class)
    public HttpResult<Object> handleUpException(HttpServletRequest request, UpException exception) {
        // 所有抛出的 UpException 都会被这个方法捕获
        return new HttpResult<>(exception.getCode(), exception.getMsg(), null);
    }
}
