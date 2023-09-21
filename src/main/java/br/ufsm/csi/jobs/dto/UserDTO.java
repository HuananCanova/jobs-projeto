package br.ufsm.csi.jobs.dto;

import br.ufsm.csi.jobs.model.User;
import lombok.Data;


public record UserDTO (Long id, String email){
    public UserDTO (User user){
        this(user.getId(), user.getEmail());
    }
}
