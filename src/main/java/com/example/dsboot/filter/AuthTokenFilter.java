package com.example.dsboot.filter;


import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@WebFilter(urlPatterns = {"/dsboot/*"},filterName ="authTokenFilter")
public class AuthTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        int test = 1;
        test = 0;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        boolean isNeedAuth = true;
        String requestPath = req.getRequestURI().toString();
        if(requestPath.contains("/dsboot/user/login")){
            isNeedAuth = false;
        }
        String token = req.getHeader("Token");
        if(isNeedAuth && (token == null || token.isEmpty())){
            OutputStreamWriter outw = null;
            try {
                outw = new OutputStreamWriter(res.getOutputStream());
                Map<String,String> result = new HashMap<>();
                result.put("code", HttpStatus.FORBIDDEN.toString());
                result.put("message","token 不正确");
                String jsonStr = JSON.toJSONString(result);
                outw.write(jsonStr);
                outw.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(outw != null){
                    outw.close();
                }
            }
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
