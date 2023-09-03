package br.ufsm.csi.jobs.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String senha;
    private String nome;
}
