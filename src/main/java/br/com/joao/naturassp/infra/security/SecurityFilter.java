package br.com.joao.naturassp.infra.security;

import br.com.joao.naturassp.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final UsuarioRepository usuarioRepository;
    private final JwtTokenService jwtTokenService;


    public SecurityFilter(UsuarioRepository usuarioRepository, JwtTokenService jwtTokenService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            var subject = jwtTokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByEmailIgnoreCase(subject).orElseThrow();

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);


    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;

    }
}
