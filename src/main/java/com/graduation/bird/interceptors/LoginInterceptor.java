package com.graduation.bird.interceptors;

import com.graduation.bird.utils.JwtUtil;
import com.graduation.bird.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //确保有token并且token没有被修改过
        if(token == null || token.equals("")){
            response.getWriter().write("token is null");
            response.setStatus(401);
            return false;
        }else {
            //调用工具类来解析token
            try{

                Map<String, Object> claims = JwtUtil.parseToken(token);

                //使用ThreadLocalUtil来保存token中的用户信息
                ThreadLocalUtil.set(claims);

                return true;

            }catch (Exception e){

                response.setStatus(401);
                return false;

            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空ThreadLocalUtil中的数据
        ThreadLocalUtil.remove();
    }
}
