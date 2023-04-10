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
 * @author Yoooum
 */
@Component
public class JwtTokenProvider {
    private int expiration = 1000 * 60 * 60 * 24;
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public String generateToken(String subject, List<String> authorities) {
        JWSSigner signer = new RSASSASigner(privateKey);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .claim("authorities", authorities)
                .issueTime(now)
                .expirationTime(expiryDate)
                .build();
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .keyID(UUID.randomUUID().toString())
                .build();
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        try {
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.getStackTrace();
            throw new RuntimeException("Could not sign JWT, reason: " + e.getMessage());
        }
    }

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
