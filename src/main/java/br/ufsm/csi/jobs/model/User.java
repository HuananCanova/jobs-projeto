package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    private String senha;

    @Column(unique = true)
    private String celular;

    @Column(nullable = true)
    private String fotoPerfilUrl;

}
