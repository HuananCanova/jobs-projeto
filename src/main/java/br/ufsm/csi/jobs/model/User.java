package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String celular;
    private String fotoPerfilUrl;

}
