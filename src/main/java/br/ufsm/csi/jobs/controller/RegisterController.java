package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/empresa")
    public ResponseEntity<Usuario> createUserCandidato(@RequestBody @Valid Usuario user, UriComponentsBuilder uriBuilder) {
        user.getRoles().add("ROLE_EMPRESA");
        userService.createUser(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }


    @PostMapping("/candidato")
    public ResponseEntity<Usuario> createUserEmpresa(@RequestBody @Valid Usuario user, UriComponentsBuilder uriBuilder) {
        user.getRoles().add("ROLE_USER");
        userService.createUser(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
