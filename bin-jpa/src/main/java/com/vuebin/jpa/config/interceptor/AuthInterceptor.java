package com.vuebin.jpa.config.interceptor;

import com.vuebin.common.core.annotation.PassToken;
import com.vuebin.common.core.util.JwtTokenUtil;
import com.vuebin.common.model.entity.User;
import com.vuebin.common.model.exception.AuthException;
import com.vuebin.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author fengjiabin
 * @date 2019/6/12 14:51
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private UserRepository userRepository;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        log.info("bin ->【AuthInterceptor】进入方法前执行...");

//        从header中取出token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

//        检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

//        除PassToken注解之外的所有接口都必须添加token参数才可以正常访问
        if (token == null) {
            throw new AuthException();
        }
        // 获取 token 中的 username
        String username = null;
        try {
            username = jwtTokenUtil.getClaimFromToken(token).getSubject();
        } catch (Exception e) {
            throw new AuthException();
        }
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new AuthException();
        }
        // 验证 token

        boolean b = jwtTokenUtil.checkToken(token);
        return b ? true : false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("bin ->【AuthInterceptor】返回视图之前执行...");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("bin ->【AuthInterceptor】方法执行完后调用...");
    }

}
