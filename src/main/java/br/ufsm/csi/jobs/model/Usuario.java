package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Campo Obrigatório!")
    private String nome;

    @Email(message = "Email inválido!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Senha não pode conter espaços em branco!")
    @Size(min = 6, max = 16)
    private String senha;

    @NotBlank(message = "Número de telefone inválido!")
    @Column(unique = true)
    private String celular;

    @Column(nullable = true)
    @NotBlank()
    private String fotoPerfilUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();


}
