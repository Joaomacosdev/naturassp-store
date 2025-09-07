package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.DadosLoginDTO;
import br.com.joao.naturassp.infra.security.JwtTokenDTO;
import br.com.joao.naturassp.infra.security.JwtTokenService;
import br.com.joao.naturassp.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }


    @PostMapping
    public ResponseEntity<JwtTokenDTO> efetuarLogin(@RequestBody @Valid DadosLoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = jwtTokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDTO(tokenJWT));
    }

}
