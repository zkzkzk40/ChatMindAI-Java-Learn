package net.chatmindai.springboot3learn.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    // 可以将密钥从环境变量或配置文件中读取
    private static final String SIGN = "XIAOXIANG_123456"; // 确保这个密钥在生成和验证时一致

    /**
     * 生成token header.payload.signature
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        // 默认1分钟过期
        instance.add(Calendar.MINUTE, 5);
        System.out.println(instance.getTime());

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create()
                .withExpiresAt(instance.getTime()); // 过期时间

        // payload
        map.forEach(builder::withClaim);

        System.out.println("Using SIGN: " + SIGN);

        // 生成token
        String token = builder.sign(Algorithm.HMAC256(SIGN));  // sign
        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        System.out.println("Using SIGN: " + SIGN);
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        System.out.println(verify);
        return verify;
    }
}
