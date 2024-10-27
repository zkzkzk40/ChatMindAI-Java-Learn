package net.chatmindai.springboot3learn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SIGN = "0123456";
    private static final int EXPIRATION_DAYS = 7;

    /**
     * 生成token
     */
    public static String getToken(Map<String, String> claims) {
        if (claims == null || claims.isEmpty()) {
            throw new IllegalArgumentException("Claims cannot be null or empty.");
        }

        Calendar expiration = Calendar.getInstance();
        // 默认7天过期
        expiration.add(Calendar.DATE, EXPIRATION_DAYS);

        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        claims.forEach(builder::withClaim);

        return builder.withExpiresAt(expiration.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Token verification failed: " + e.getMessage(), e);
        }
    }
}
