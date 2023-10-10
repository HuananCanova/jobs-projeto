package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.dto.UserDTO;
import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid Usuario user, UriComponentsBuilder uriBuilder) {
        user.getRoles().add("ROLE_USER");
        userService.createUser(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }


/*    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.listUsers();
        return ResponseEntity.ok(users);
    }*/

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findALlUsers();
    }


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){

            return userService.findUser(id);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuário com ID " + id + " foi deletado com sucesso");

    }

}
