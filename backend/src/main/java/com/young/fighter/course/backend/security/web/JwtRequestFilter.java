//package com.young.fighter.course.backend.security.web;
//
//import com.young.fighter.course.backend.security.util.JwtTokenUtil;
//import com.young.fighter.course.backend.service.api.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class JwtRequestFilter implements WebFilter {
//    private final JwtTokenUtil jwtTokenUtil;
//    private final UserService userService;
//    private final String HEADER = "Authorization";
//    private static final String PREFIX = "Bearer ";
//
//    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, UserService userService) {
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userService = userService;
//    }
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        exchange.getRequest().getHeaders();
//        return null;
//    }
//
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
////            throws ServletException, IOException {
////        final String requestTokenHeader = request.getHeader(HEADER);
////        Long userId = null;
////        String jwtToken = null;
////        if (requestTokenHeader != null && requestTokenHeader.startsWith(PREFIX)) {
////            jwtToken = requestTokenHeader.substring(7);
////            log.info("Token: {}", jwtToken);
////            try {
////                userId = jwtTokenUtil.getUserId(jwtToken);
////            } catch (IllegalArgumentException e) {
////                log.info("Unable to get JWT Token");
////            } catch (ExpiredJwtException e) {
////                log.info("JWT Token has expired");
////            } catch (SignatureException exception) {
////                log.info("Wrong jwt signature");
////            }
////        }
////        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            JwtUser jwtUser = jwtTokenUtil.validateToken(jwtToken);
////            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////                    jwtUser.getUserId()
////                    , null,
////                    new HashSet<GrantedAuthority>(jwtUser.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
////            );
////            usernamePasswordAuthenticationToken
////                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
////        }
////        chain.doFilter(request, response);
////    }
//}
