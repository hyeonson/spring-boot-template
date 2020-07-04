package com.hyeonson.yajalal.authentication;

import com.hyeonson.yajalal.dto.UserNickAndIdx;
import com.hyeonson.yajalal.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String JWT = request.getHeader("JWT");
//        log.error("JWT: " + JWT);
        if (JWT == null) {
            next(request, response, filterChain);
            return;
        }

        UserNickAndIdx userNickAndIdx;
        try {
            userNickAndIdx = jwtUtil.getUserNickAndIdxFromToken(JWT);
        } catch (Exception e) {
            log.error("jwt parsing error: {}, token: {}, ", e.toString(), JWT);
            next(request, response, filterChain);
            return;
        }

        if (userNickAndIdx == null) {
            next(request, response, filterChain);
            return;
        }

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
//        final UserDetails user = new User(username, "", true, true, true, true, authorities);
        userNickAndIdx.setAuthorities(authorities);

        if (jwtUtil.validateToken(JWT)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userNickAndIdx, null, userNickAndIdx.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private void next(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
//        log.info("bad cookie token");
        chain.doFilter(request, response);
    }
}
