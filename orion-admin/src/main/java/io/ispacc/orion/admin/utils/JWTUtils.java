package io.ispacc.orion.admin.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import io.ispacc.orion.admin.module.admin.entity.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON Web Token (JWT)工具类
 */
public class JWTUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 入口方法 根据 username 创建 Token
     *
     * @param user 用户信息
     * @Return String
     */
    public static String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getUserId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createToken(claims, "orion-secret".getBytes());
    }

    /**
     * 创建HS256(HmacSHA256) JWT Token
     *
     * @param payload 荷载信息
     * @param key     HS256(HmacSHA256)密钥
     * @return JWT Token
     */
    public static String createToken(Map<String, Object> payload, byte[] key) {

        return createToken(null, payload, key);
    }

    /**
     * 创建HS256(HmacSHA256) JWT Token
     *
     * @param headers 头信息
     * @param payload 荷载信息
     * @param key     HS256(HmacSHA256)密钥
     * @return JWT Token
     */
    public static String createToken(Map<String, Object> headers, Map<String, Object> payload, byte[] key) {
        return JWT.create()
                .addHeaders(headers)
                .addPayloads(payload)
                .setKey(key)
                .sign();
    }

    /**
     * 创建JWT Token
     *
     * @param payload 荷载信息
     * @param signer  签名算法
     * @return JWT Token
     */
    public static String createToken(Map<String, Object> payload, JWTSigner signer) {
        return createToken(null, payload, signer);
    }

    /**
     * 创建JWT Token
     *
     * @param headers 头信息
     * @param payload 荷载信息
     * @param signer  签名算法
     * @return JWT Token
     */
    public static String createToken(Map<String, Object> headers, Map<String, Object> payload, JWTSigner signer) {
        return JWT.create()
                .addHeaders(headers)
                .addPayloads(payload)
                .setSigner(signer)
                .sign();
    }

    /**
     * 解析JWT Token
     *
     * @param token token
     * @return {@link JWT}
     */
    public static JWT parseToken(String token) {
        return JWT.of(token);
    }

    /**
     * 验证JWT Token有效性
     *
     * @param token JWT Token
     * @param key   HS256(HmacSHA256)密钥
     * @return 是否有效
     */
    public static boolean verify(String token, byte[] key) {
        return JWT.of(token).setKey(key).verify();
    }

    /**
     * 验证JWT Token有效性
     *
     * @param token  JWT Token
     * @param signer 签名器
     * @return 是否有效
     */
    public static boolean verify(String token, JWTSigner signer) {
        return JWT.of(token).verify(signer);
    }
}
