package com.vuebin.common.core.util;

import com.vuebin.common.model.exception.RequestEmptyException;
import com.vuebin.common.model.validator.BaseValidatingParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 校验参数中的参数是否符合规则
 *
 * @author fengjiabin
 * @date 2019/6/11 22:06
 */
@Slf4j
public class CheckUtil {

    public static void validateParameters(Object[] methodParams) {
        for (Object methodParam : methodParams) {
            if (methodParam instanceof BaseValidatingParam) {
                BaseValidatingParam baseValidatingParam = (BaseValidatingParam) methodParam;
                String checkResult = baseValidatingParam.checkParam();

                //如果校验结果不为空，则代表参数校验有空的或者不符合规则的，并且checkResult为参数错误的提示信息
                if (ValidateUtil.isNotEmpty(checkResult)) {
                    log.info("bin ->【CheckUtil】...");
                    throw new RequestEmptyException(checkResult);
                }
            }
        }
    }

}
