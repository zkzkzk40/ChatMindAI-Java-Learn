package com.chatmindai.springboot3learn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SING = "0123456";

    /**
     * 生成token
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}