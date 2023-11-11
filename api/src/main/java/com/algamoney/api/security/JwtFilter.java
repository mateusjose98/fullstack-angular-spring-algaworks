package com.algamoney.api.security;

import com.algamoney.api.model.Usuario;
import com.algamoney.api.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    final JWTTokenService jwtTokenService;
    final UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = getTokenFrom(request);
        if(tokenJWT != null) {
            var subject = jwtTokenService.getSubject(tokenJWT);
            Usuario user = usuarioService.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getPermissoes().stream().map(permissao -> new SimpleGrantedAuthority(permissao.getDescricao())).collect(Collectors.toSet()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }



        filterChain.doFilter(request, response);

    }

    private String getTokenFrom(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;

    }
}
