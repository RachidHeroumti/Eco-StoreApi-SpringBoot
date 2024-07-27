package com.demo.AuthConfigs.configs;

import com.demo.AuthConfigs.models.User;
import com.demo.AuthConfigs.repositories.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private  UserDetailsServices userDetailsService;
    @Autowired
    private  JwtUtils jwtUtil ;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = req.getHeader("AUTHORIZATION");
        final String userEmail;
        final String jwtToken;

        if(authHeader==null||authHeader.startsWith("Bearn")){
            filterChain.doFilter(req,res);
            return;
        }

        jwtToken = authHeader.substring(7);

        userEmail =jwtUtil.extractUsername(jwtToken);

        if(userEmail!=null&& SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(userEmail) ;
             if(jwtUtil.validateToken(jwtToken,userDetails)){
                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
        }
filterChain.doFilter(req,res);
    }
}
