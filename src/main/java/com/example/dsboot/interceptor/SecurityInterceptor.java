package com.example.dsboot.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(validateToken(request,handler)){
            return true;
        }
        response.sendError(HttpStatus.FORBIDDEN.value(),"无权限");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean validateToken(HttpServletRequest request, Object handler){
        boolean pass = true;
        String token = request.getHeader("Token");
        if(token == null ||token.isEmpty()){
            pass = false;
        }
        return pass;
    }
}
