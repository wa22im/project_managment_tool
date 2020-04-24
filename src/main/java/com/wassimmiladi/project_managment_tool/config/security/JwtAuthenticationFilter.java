package com.wassimmiladi.project_managment_tool.config.security;

import com.wassimmiladi.project_managment_tool.core.myusers.entity.MyUser;
import com.wassimmiladi.project_managment_tool.core.myusers.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wassimmiladi.project_managment_tool.config.security.SecurityConstants.HEADER_STRING;
import static com.wassimmiladi.project_managment_tool.config.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

     @Autowired
     private  JwtTokenProvider jwtTokenProvider ;

     @Autowired
     private CustomUserDetailsService customUserDetailsService ;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWTFromRequest(httpServletRequest) ;
            if ( StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt)){

                Long userId = jwtTokenProvider.getUSerIdFromJWT(jwt) ;

                MyUser userDetails  = customUserDetailsService.loadUserById(userId) ;

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails , null , null
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }


        } catch (Exception ex) {
            logger.error("could not ser user authentication in security " , ex);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }



private  String getJWTFromRequest(HttpServletRequest httpRequest) {
        String bearerjwt = httpRequest.getHeader(HEADER_STRING) ;
        if (StringUtils.hasText(bearerjwt)&&bearerjwt.startsWith(TOKEN_PREFIX)){
            return  bearerjwt.substring(7,bearerjwt.length());
        }
        return null  ;

    }

}
