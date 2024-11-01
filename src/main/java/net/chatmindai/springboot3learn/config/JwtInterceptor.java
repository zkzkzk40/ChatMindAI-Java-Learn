package net.chatmindai.springboot3learn.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.exception.ServiceException;
import net.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private static final String ERROR_CODE_401 = "401";

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServiceException {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(401, "无token，请重新登录");
        }
        // 获取 token 中的userId
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(401, errMsg);
        }

        //根据token中的userId查询数据库
        User user=userMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException(401, "用户不存在，请重新登录");
        }

        try {
            // 用户密码加签验证 token,由于没有password，使用email验证
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getEmail())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(401, "token验证失败，请重新登录");
        }
        return true;
    }
}
