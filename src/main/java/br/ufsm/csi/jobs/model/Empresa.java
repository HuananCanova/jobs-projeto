package br.ufsm.csi.jobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social")
    private String razaoSocial;

    private String CNPJ;
    private String email;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
