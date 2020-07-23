package com.IAM.shoutOut.authorization.filter;


import com.IAM.shoutOut.authorization.authService.UserDetailsServiceImpl;
import com.IAM.shoutOut.authorization.util.JWTProviderUtil;
import com.IAM.shoutOut.configuration.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTProviderUtil jwtProviderUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTAuthFilter(@Lazy JWTProviderUtil jwtProviderUtil, UserDetailsServiceImpl userDetailsService, JWTConfig jwtConfig) {
        this.jwtConfig=jwtConfig;
        this.jwtProviderUtil=jwtProviderUtil;
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader=httpServletRequest.getHeader(jwtConfig.getHeader());
        if(requestHeader==null || !requestHeader.startsWith(jwtConfig.getPrefix())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String token = requestHeader.replace(jwtConfig.getPrefix(), "");
        String username=null;
        try{
             username=jwtProviderUtil.getUserEmailFromToken(token);
             if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                 UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                 if(jwtProviderUtil.validateToken(token,userDetails)){
                     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,null);
                     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                 }
             }

        }catch(Exception e){
            SecurityContextHolder.clearContext();
            System.out.println(e);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


}
