package com.forum.portal.interceptor;


import com.forum.portal.entity.User;
import com.forum.portal.service.UserService;
import com.forum.portal.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Value("${cookie_name}")
    private String CookieName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "QUARK_TOKEN");
        if (token == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        User user = userService.getUserByApi(token);

        if(user == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
