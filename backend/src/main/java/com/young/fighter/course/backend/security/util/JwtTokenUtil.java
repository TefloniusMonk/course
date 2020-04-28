package com.young.fighter.course.backend.security.util;

import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.security.entity.JwtUser;
import com.young.fighter.course.backend.security.type.JwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final io.jsonwebtoken.SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private final static String SECRET = "secret";


    public Long getUserId(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()
                .get(JwtClaims.USER_ID.getKey(), Long.class);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(JwtUser user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaims.AUTHORITIES.getKey(), user.getAuthorities());
        claims.put(JwtClaims.USER_ID.getKey(), user.getUserId());
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .addClaims(claims)
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }


    public JwtUser validateToken(@NotNull String token) {
        if (isTokenExpired(token)) {
            throw new BusinessLogicException("token.expired");
        }
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return new JwtUser(
                claims.get(JwtClaims.CUSTOMER_ID.getKey(), Long.class),
                claims.get(JwtClaims.USER_ID.getKey(), Long.class),
                claims.get(JwtClaims.AUTHORITIES.getKey(), List.class));
    }
}

