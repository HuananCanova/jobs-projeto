package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.dto.RequestDTO;
import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.security.TokenServiceJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenServiceJWT tokenServiceJWT;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenServiceJWT tokenServiceJWT) {
        this.authenticationManager = authenticationManager;
        this.tokenServiceJWT = tokenServiceJWT;
    }

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid DadosAuth dados) {
        try {

            Authentication authenticated = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication auth = authenticationManager.authenticate(authenticated);

            User user = (User) auth.getPrincipal();
            String token = tokenServiceJWT.generateToken(user);

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    private record DadosTokenJWT(String token) {
    }
    private record DadosAuth(String email, String senha){}
}
