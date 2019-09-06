package com.vuebin.common.core.aop;

import com.vuebin.common.core.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import static com.vuebin.common.model.constants.AopSortConstant.PARAM_VALIDATE_SORT;

/**
 * 参数校验的aop
 *
 * @author fengjiabin
 * @date 2019/6/11 22:09
 */
@Slf4j
@Aspect
@Order(PARAM_VALIDATE_SORT)
public class ParamValidateAop {

    @Pointcut(value = "@annotation(com.vuebin.common.core.annotation.ParamValidator)")
    private void cutService() {

    }

    @Around("cutService()")
    public Object doInvoke(ProceedingJoinPoint point) throws Throwable {

        log.info("bin ->【ParamValidateAop】参数校验Aop已执行...");

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //如果请求参数为空，直接跳过aop
        if (methodParams == null || methodParams.length <= 0) {
            return point.proceed();
        } else {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            CheckUtil.validateParameters(methodParams);
            return point.proceed();
        }
    }

}
