package py.com.mcs.tweet.service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.exceptions.TweetException;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class JwtServiceImpl implements JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${tweet.jwt.secretKey}")
    private String secretKey;
    @Value("${tweet.jwt.expiration-time}")
    private Long expirationTime;

    @Override
    public String generateToken(UserGetRes user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date tokenExpires = convertirADate(localDateTime.plusDays(expirationTime));
        return Jwts.builder()
                .claim("tweetUser", user)
                .setSubject(user.getId().toString())
                .setId(TraceContextHolder.getLogId())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpires)
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractUserDocument(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    @Override
    public UserGetRes isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            if (expirationDate == null && expirationDate.before(new Date()))
                throw new TweetException("Access Token expirado", HttpStatus.UNAUTHORIZED);

            Map<String, Object> userMap = (Map<String, Object>) claims.get("tweetUser");
            return convertMapToUserGetRes(userMap);

        } catch (Exception e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "isTokenValid:Error", e.getMessage());
            throw new TweetException("Access token invalido", HttpStatus.UNAUTHORIZED);
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private UserGetRes convertMapToUserGetRes(Map<String, Object> userMap) {
        UserGetRes res = new UserGetRes();
        res.setId(Long.valueOf(userMap.get("id").toString()) );
        res.setUserName(userMap.get("userName").toString());
        res.setEmail(userMap.get("email").toString());
        res.setFullName(userMap.get("fullName").toString());

        return res;
    }

    public static Date convertirADate(LocalDateTime localDateTime) {
        // Convertir LocalDateTime a un objeto Date
        // Usar la zona horaria predeterminada del sistema
        return java.util.Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}
