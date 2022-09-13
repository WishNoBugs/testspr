package com.example.testspr.util;


import com.example.testspr.constants.CommConstant;
import com.example.testspr.domain.User;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * MD5加密
 *
 * @author wangtao
 * @create 2018-12-17 15:31
 */
@Component
@Slf4j
public class JwtTokenUtil {


    public static String SECRET = "jdksagh213h"; //秘钥
    public static Long EXPIRATION = 7l; //过期时间
    public static String TOKENHEAD = "Bearer";//开头
    public static String TOKENHEADER = "Authorization";

    /**
     * 从token中获取用户account
     * @param token
     * @return
     */
    public static String getUserAccountFromToken(String token) {
        String useraccount;
        try {
            final Claims claims = getClaimsFromToken(token);
            useraccount = claims.getSubject();
        } catch (Exception e) {
            useraccount = null;
        }
        return useraccount;
    }

    public static String getLoginRecordKey(String token){
        String loginkey;
        try {
            final Claims claims = getClaimsFromToken(token);
            loginkey = claims.get(CommConstant.CLAIM_KEY_LOGINRECORD).toString();
        } catch (Exception e) {
            loginkey = null;
        }
        return loginkey;
    }

    /**
     * 从token中获取创建时间
     * @param token
     * @return
     */
    public static Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CommConstant.CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取token的过期时间
     * @param token
     * @return
     */
    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 从token中获取claims
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            claims = null;
        }
        return claims;
    }

    /**
     * 生存token的过期时间
     * @return
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000 * 60);
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        Boolean result= expiration.before(new Date());
        return result;
    }



    /**
     * 生成token
     * @param userDetails
     * @return
     */
    public static String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommConstant.CLAIM_KEY_USER_ACCOUNT, userDetails.getUsername());
        claims.put(CommConstant.CLAIM_KEY_USER_ROLE, userDetails.getRole());
        claims.put(CommConstant.CLAIM_KEY_CREATED, new Date());
        return TOKENHEAD+generateToken(claims);
    }

    static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * token 是否可刷新
     * @param token
     * @return
     */
    public static Boolean canTokenBeRefreshed(String token) {
        final Date created = getCreatedDateFromToken(token);
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CommConstant.CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证token
     * @param token
     * @param userDetails
     * @return
     */
    public static Boolean validateToken(String token, User userDetails) {
        final String useraccount = getUserAccountFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        Boolean result= (
                useraccount.equals(userDetails.getUsername())
                        && !isTokenExpired(token)
        );
        return result;
    }

    /**
     * 获取当前登录人
     * @return
     */
    public static User getCurrentUser(){
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

