package com.innovationcamp.messenger.domain.user.jwt;

import com.innovationcamp.messenger.domain.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    public static final String ACCESS_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    public static final String EMAIL_KEY = "email";
    public static final String NICKNAME_KEY = "nickname";
    private static final String BEARER_PREFIX = "Bearer ";
    public final long ACCESS_TOKEN_TIME = 24 * 60 * 60 * 1000L; // 24시간

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String substringToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) { return token.substring(7);}
        throw new NullPointerException("유효한 토큰이 아닙니다");
    }

    public void addJwtToCookie(String token, HttpServletResponse res) {
        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20");

            Cookie cookie = new Cookie(ACCESS_HEADER, token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge((int)ACCESS_TOKEN_TIME/1000);
            res.addCookie(cookie);

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
    }

    public String getTokenFromRequest(HttpServletRequest req) {
        String token = req.getHeader(ACCESS_HEADER);
        if(token != null) {
            try {
                return URLDecoder.decode(token, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.info(e.getMessage());
                return null;
            }
        }
        return null;
    }

    public String getTokenFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ACCESS_HEADER)) {
                    if (cookie.getValue() != null) {
                        try {
                            return URLDecoder.decode(cookie.getValue(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            log.info(e.getMessage());
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    // 토큰 생성
    public String createToken(User user) {
        Date date = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(user.getId()))
                        .claim(EMAIL_KEY, user.getEmail())
                        .claim(NICKNAME_KEY, user.getUsername())
                        .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        } return false;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public void deleteJwtCookie(HttpServletResponse res) {
        Cookie cookie = new Cookie(ACCESS_HEADER, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }
}
