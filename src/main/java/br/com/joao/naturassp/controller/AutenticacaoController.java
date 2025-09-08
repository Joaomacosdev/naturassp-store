package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.DadosLoginDTO;
import br.com.joao.naturassp.infra.security.DadosRefreshToken;
import br.com.joao.naturassp.infra.security.JwtTokenDTO;
import br.com.joao.naturassp.infra.security.JwtTokenService;
import br.com.joao.naturassp.model.Usuario;
import br.com.joao.naturassp.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtTokenService jwtTokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenService = jwtTokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> efetuarLogin(@RequestBody @Valid DadosLoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = jwtTokenService.generateToken((Usuario) authentication.getPrincipal());
        String refreshToken = jwtTokenService.generateRefreshToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDTO(tokenJWT, refreshToken));
    }

    @PostMapping("/alterar-token")
    public ResponseEntity<JwtTokenDTO> atualizarToken(@RequestBody @Valid DadosRefreshToken dados) {
        var refreshToken = dados.refreshToken();
        Long idUsuario = Long.valueOf(jwtTokenService.getSubject(refreshToken));
        var usuario = usuarioRepository.findById(idUsuario).orElseThrow();

        String tokenAcesso = jwtTokenService.generateToken(usuario);
        String tokenAtualizacao = jwtTokenService.generateRefreshToken(usuario);
        return ResponseEntity.ok().body(new JwtTokenDTO(tokenAcesso, tokenAtualizacao));
    }

}
