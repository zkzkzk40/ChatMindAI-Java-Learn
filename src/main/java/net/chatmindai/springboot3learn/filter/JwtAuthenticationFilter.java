package net.chatmindai.springboot3learn.filter;

import net.chatmindai.springboot3learn.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println("token: " + token);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 Bearer 前缀
            try {
                // 验证和解析 JWT
                DecodedJWT decodedJWT = JWTUtils.verify(token);
                String userId = decodedJWT.getClaim("id").asString();
                String username = decodedJWT.getClaim("name").asString();

                // 设置用户信息到 SecurityContext 中
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        userId, "", new ArrayList<>());
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // JWT 验证失败，设置为未认证状态
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: " + e.getMessage());
                return; // 终止请求
            }
        }
        filterChain.doFilter(request, response);
    }
}
