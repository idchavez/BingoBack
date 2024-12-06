package com.bingoback.auth.config.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bingoback.auth.persistence.repository.InvalidarTokenRepository;
import com.bingoback.auth.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private InvalidarTokenRepository invalidarTokenRepository;

    @Autowired
    public JwtTokenValidator(JwtUtils jwtUtils, InvalidarTokenRepository invalidarTokenRepository){
        this.jwtUtils = jwtUtils;
        this.invalidarTokenRepository = invalidarTokenRepository;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null){
            jwtToken = jwtToken.substring(7);

            if(invalidarTokenRepository.existsByToken(jwtToken)) {
                throw new JWTVerificationException("Token es invalido");
            } else {
                try {
                    DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

                    String username = jwtUtils.extractUsername(decodedJWT);
                    String stringAuthoritites = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

                    Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthoritites);

                    SecurityContext context = SecurityContextHolder.getContext();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                    System.out.println("Usuario autenticado: " + username);
                } catch (Exception e) {
                    System.out.println("Error en la validacion del token: " + e.getMessage());
                    throw new JWTVerificationException("Token no valido");
                }
            }
        } else {
            System.out.println("Token no presente en la solicitud");
        }
        filterChain.doFilter(request,response);
    }
}
