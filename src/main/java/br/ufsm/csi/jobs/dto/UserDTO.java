package br.ufsm.csi.jobs.dto;

import br.ufsm.csi.jobs.model.User;
import lombok.Data;

import java.util.Set;


public record UserDTO(Long id, String email, Set<String> roles) {
    public UserDTO(User user) {
        this(user.getId(), user.getEmail(), user.getRoles());
    }
}
