package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.dto.RequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid RequestDTO dados) {
        Authentication authenticated = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        Authentication auth = authenticationManager.authenticate(authenticated);
        return ResponseEntity.ok().body(auth.getPrincipal());
    }
}
