package br.ufsm.csi.jobs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razaoSocial;;
    private String CNPJ;
    private String email;
    @Embedded
    private Endereco endereco;
}
