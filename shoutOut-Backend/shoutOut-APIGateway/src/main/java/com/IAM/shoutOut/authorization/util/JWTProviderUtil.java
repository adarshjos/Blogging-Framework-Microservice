package com.IAM.shoutOut.authorization.util;


import com.IAM.shoutOut.authorization.model.UserDetailsImpl;
import com.IAM.shoutOut.exceptions.shoutOutExceptions;
import com.IAM.shoutOut.configuration.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTProviderUtil {
    private KeyStore keyStore;
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTProviderUtil(JWTConfig jwtConfig) {
        super();
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream(jwtConfig.getCertificateRes());
            keyStore.load(resourceAsStream,jwtConfig.getSecret().toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e ) {
            throw new shoutOutExceptions("Exception Occurred while loading key");
        }
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getPublicKey()   ).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetailsImpl userDetails){
        HashMap<String,Object>claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(HashMap<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*jwtConfig.getExpiration()))
                .signWith(getPrivateKey()).compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey)keyStore.getKey(jwtConfig.getAlias(),jwtConfig.getSecret().toCharArray());
        } catch (KeyStoreException |NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new shoutOutExceptions("Exception Occurred while fetching private key");
        }
    }
    private PublicKey getPublicKey(){
        try {
            return keyStore.getCertificate(jwtConfig.getAlias()).getPublicKey();
        } catch (KeyStoreException e) {
            throw new shoutOutExceptions("Exception Occurred while fetching public key");
        }
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String username=getUserEmailFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate=getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }

}
