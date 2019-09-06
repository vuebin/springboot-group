package com.vuebin.common.model.constants;

/**
 * 所有AOP的顺序排序（数字越低越靠前）
 *
 * @author fengjiabin
 * @date 2019/6/11 22:22
 */
public interface AopSortConstant {

    /**
     * 全局拦截异常捕获
     */
    int GLOBAL_EXCEPTION_HANDLER_SORT = 1;

    /**
     * 参数校验aop执行顺序
     */
    int PARAM_VALIDATE_SORT = 2;

}
