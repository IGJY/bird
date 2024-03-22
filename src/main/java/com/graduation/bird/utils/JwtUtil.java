package com.graduation.bird.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //密钥
    private static final String SECRET = "graduation-bird";

    //接收业务数据。生成token，并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(SECRET));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) throws Exception{
        try {
            return JWT.decode(token).getClaim("claims").asMap();
        } catch (Exception e) {
            //TODO 这里要抛什么类型的异常
            throw new Exception("token解析失败");
        }
    }

}
