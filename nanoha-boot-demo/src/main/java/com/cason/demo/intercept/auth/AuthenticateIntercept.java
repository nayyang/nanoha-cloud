package com.cason.demo.intercept.auth;

import com.cason.demo.Service.AdminsService;
import com.cason.demo.common.HeaderCons;
import com.cason.demo.utils.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 登录令牌权限校验拦截器
 */
public class AuthenticateIntercept extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(AuthenticateIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("AuthenticateIntercept----------校验，do something-----{}",request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authenticate authenticate = method.getAnnotation(Authenticate.class);
        if (Objects.nonNull(authenticate)) {
            String accessToke = request.getHeader(HeaderCons.ACCESS_TOKEN);
            String mobile = request.getHeader(HeaderCons.MOBILE);
            if(!getAdminsService().checkToken(mobile,accessToke)){
                logger.info("无效的访问令牌 {}", accessToke);
                throw new RuntimeException("令牌失效！");
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}

    public AdminsService getAdminsService() {
        return SpringContextUtil.getBean(AdminsService.class);
    }
}
