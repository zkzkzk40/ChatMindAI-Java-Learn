package net.chatmindai.springboot3learn.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.chatmindai.springboot3learn.util.JwtUtil;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的令牌
        String token = request.getHeader("token");

        // 使用map储存响应信息
        Map<String, Object> map = new HashMap<>();

        if (token == null || token.isEmpty()) {
            map.put("msg", "token不能为空");
            map.put("state", "false");
            sendErrorResponse(response, map);
            return false;
        }

        try {
            // 验证令牌
            DecodedJWT verify = JwtUtil.verify(token);
            return true; // 验证通过
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            map.put("msg", "token无效");
        }

        map.put("state", "false");
        sendErrorResponse(response, map);
        return false; // 验证失败
    }

    // 发送错误响应的方法
    private void sendErrorResponse(HttpServletResponse response, Map<String, Object> map) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().println(json);
    }
}
