package com.prprv.jpa.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * JWT提供者，用于生成和验证JWT
 * @author Yoooum
 */
@Component
public class JwtTokenProvider {
    /**
     * 过期时间
     */
    private final int expiration = 1000 * 60 * 60 * 24;
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    /**
     * 在 JwtTokenProvider 对象创建后，使用 @PostConstruct 注解标注的方法，会自动调用该方法，
     * 用于初始化公钥和私钥，这里使用了 RSA 算法生成了一个 2048 位的密钥对。
     * @throws NoSuchAlgorithmException 无此算法异常
     */
    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    /**
     * 生成JWT
     * @param subject JWT主题，一般是用户名
     * @param authorities 权限，权限放在JWT的payload中，是为了方便在验证JWT的时候，不需要再去数据库中查询用户的权限
     * @return JWT 生成的JWT
     */
    public String generateToken(String subject, List<String> authorities) {
        // JWT的签名部分
        JWSSigner signer = new RSASSASigner(privateKey);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        // JWT的payload部分
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .claim("authorities", authorities)
                .issueTime(now)
                .expirationTime(expiryDate)
                .build();
        // JWT的header部分
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .keyID(UUID.randomUUID().toString())
                .build();
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        try {
            // 签名
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.getStackTrace();
            throw new RuntimeException("Could not sign JWT, reason: " + e.getMessage());
        }
    }

    /**
     * 验证JWT
     * @param token JWT
     * @return boolean 是否验证通过
     */
    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier(publicKey);
            return signedJWT.verify(verifier);
        } catch (JOSEException | ParseException e) {
            e.getStackTrace();
            throw new RuntimeException("Could not verify JWT, reason: " + e.getMessage());
        }
    }

    /**
     * 从JWT中提取用户名
     * @param token JWT
     * @return String 用户名
     */
    public String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            return claimsSet.getSubject();
        } catch (ParseException e) {
            e.getStackTrace();
            throw new RuntimeException("Could not parse JWT, reason: " + e.getMessage());
        }
    }
}
