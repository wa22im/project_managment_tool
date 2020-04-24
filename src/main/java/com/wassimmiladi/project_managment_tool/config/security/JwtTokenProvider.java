package com.wassimmiladi.project_managment_tool.config.security;

import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.wassimmiladi.project_managment_tool.config.security.SecurityConstants.EXPRIRATION_TIME;
import static com.wassimmiladi.project_managment_tool.config.security.SecurityConstants.SECRET;


@Component
public class JwtTokenProvider {

    //Generate the token

    public String generateToken(Authentication authentication) {

        MyUser user = (MyUser) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiredate = new Date(now.getTime() + EXPRIRATION_TIME);
        String userId = Long.toString(user.getId());
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);
        claims.put("username", user.getUsername());
        claims.put("fullname", user.getFullName());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // validate token

    public boolean validateToken(String token) {
        try {

            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("invalid JWT ");

        } catch (MalformedJwtException ex) {

            System.out.println("INVALIID JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("expired jwt token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("unsupoted");
        } catch (IllegalArgumentException ex) {
            System.out.println("claims string empty");
        }
return  false ;
    }
    // get user id from token
    public Long getUSerIdFromJWT(String token )
    {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()  ;
        String id = (String)claims.get("id");
        System.out.println(id);

        return  Long.parseLong(id) ;

    }



}
