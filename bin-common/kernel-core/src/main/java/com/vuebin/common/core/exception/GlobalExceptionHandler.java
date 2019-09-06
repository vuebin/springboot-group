package com.vuebin.common.core.exception;

import com.vuebin.common.model.exception.AuthException;
import com.vuebin.common.model.exception.RequestEmptyException;
import com.vuebin.common.model.exception.enums.CoreExceptionEnum;
import com.vuebin.common.model.system.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.vuebin.common.model.constants.AopSortConstant.GLOBAL_EXCEPTION_HANDLER_SORT;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengjiabin
 * @date 2019/6/12 8:44
 */
@Slf4j
@RestControllerAdvice
@Order(GLOBAL_EXCEPTION_HANDLER_SORT)
public class GlobalExceptionHandler {

    /**
     * 拦截请求为空的异常
     */
    @ExceptionHandler(RequestEmptyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData emptyRequest(RequestEmptyException e) {
        log.info("bin ->【GlobalExceptionHandler】全局拦截异常捕获器拦截空请求已执行...");
        return ResponseData.error(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截请求为空的异常
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData authException(AuthException e) {
        log.info("bin ->【GlobalExceptionHandler】全局拦截异常捕获器拦截空请求已执行...");
        return ResponseData.error(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData serverError(Exception e) {
        log.info("bin ->【GlobalExceptionHandler】全局拦截异常捕获器拦截未知异常已执行...");
        return ResponseData.error(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage());
    }

}
