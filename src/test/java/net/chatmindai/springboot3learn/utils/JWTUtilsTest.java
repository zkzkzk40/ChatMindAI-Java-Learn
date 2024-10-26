package net.chatmindai.springboot3learn.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class JWTUtilsTest {

    private long time = 1000 * 60 * 60 * 1;
    private String sign = "admin";

    //创建JWT
    @Test
    public void createJwt() {
        HashMap<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        // 20秒后令牌token失效
        instance.add(Calendar.SECOND,60);

        String token = JWT.create()
                .withHeader(map) // header可以不写，因为默认值就是它
                .withClaim("userId", 21)  //payload
                .withClaim("username", "xiaoshuang")
                .withExpiresAt(instance.getTime()) // 指定令牌的过期时间
                .sign(Algorithm.HMAC256("XIAOSHUANG"));//签名

        System.out.println(token);
    }



    //解析JWT
    @Test
    public void parseJwt() {
        // 通过签名生成验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("XIAOSHUANG")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3Mjk5Mzg5OTQsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb3NodWFuZyJ9.ktQ4CvdcAu3QrbtVL5d1m_v_WbLcptZKmkKgmKqkbZw");
        System.out.println(verify.getClaim("userId"));
        System.out.println(verify.getClaim("username"));
        System.out.println("令牌过期时间："+verify.getExpiresAt());
    }

}

