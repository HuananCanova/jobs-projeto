package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "vagas")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String requisitos;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
