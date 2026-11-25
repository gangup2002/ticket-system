package com.example.ticketreservation.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
 @Value("${app.jwt.secret}") private String secret;
 @Value("${app.jwt.expiration-ms}") private long expiration;
 private SecretKey key(){return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String generate(String username,String role){Date now=new Date();return Jwts.builder().setSubject(username).claim("role",role).setIssuedAt(now).setExpiration(new Date(now.getTime()+expiration)).signWith(key(),SignatureAlgorithm.HS384).compact();}
 public String username(String token){return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();}
 public long getExpiration(){return expiration;}
 public boolean valid(String token){try{Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);return true;}catch(Exception e){return false;}}
}
