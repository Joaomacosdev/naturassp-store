package br.com.joao.naturassp.infra.security;

import br.com.joao.naturassp.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("naturassp")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token " + exception);
        }
    }

    public String generateRefreshToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("naturassp")
                    .withSubject(usuario.getId().toString())
                    .withExpiresAt(dataExpiracao(120))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token " + exception);
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("naturassp")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao validar token ou token invalido " + token + " " + exception);
        }
    }

    private Instant dataExpiracao(Integer minutos) {
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
    }


}
