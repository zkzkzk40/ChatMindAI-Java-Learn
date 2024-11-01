package com.example.springboot3learn.util;

import com.example.springboot3learn.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key"; // 替换为实际的密钥
    private static final long EXPIRATION_TIME = 864_000_00; // 1天有效期，单位是毫秒

    // 生成 JWT
    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getName()) // 使用用户名作为主题
                .claim("id", user.getId()) // 将用户ID作为额外的声明
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 验证 JWT 并获取用户信息
    public static User validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        User user = new User();
        user.setId(claims.get("id", Long.class)); // 获取用户ID
        user.setName(claims.getSubject()); // 获取用户名

        return user;
    }
}
