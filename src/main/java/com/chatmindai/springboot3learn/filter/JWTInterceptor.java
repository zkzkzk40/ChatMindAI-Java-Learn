package com.chatmindai.springboot3learn.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatmindai.springboot3learn.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.auth0.jwt.exceptions.SignatureVerificationException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
/**
 * 配置拦截器
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        //获取请求头中的令牌
        String token = request.getHeader("token");

        try {
            //验证令牌
            DecodedJWT verify = JwtUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名");
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        } catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","token算法不一致");
        } catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效");
        }
        map.put("state","flase");

        //将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
