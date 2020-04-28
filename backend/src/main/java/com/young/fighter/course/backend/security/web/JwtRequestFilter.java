package com.young.fighter.course.backend.security.web;

import com.young.fighter.course.backend.security.entity.JwtUser;
import com.young.fighter.course.backend.security.util.JwtTokenUtil;
import com.young.fighter.course.backend.service.api.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(HEADER);
        Long userId = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith(PREFIX)) {
            jwtToken = requestTokenHeader.substring(7);
            log.info("Token: {}", jwtToken);
            try {
                userId = jwtTokenUtil.getUserId(jwtToken);
            } catch (IllegalArgumentException e) {
                log.info("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.info("JWT Token has expired");
            } catch (SignatureException exception) {
                log.info("Wrong jwt signature");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtUser jwtUser = jwtTokenUtil.validateToken(jwtToken);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    jwtUser.getUserId()
                    , null,
                    new HashSet<GrantedAuthority>(jwtUser.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
            );
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }
}
