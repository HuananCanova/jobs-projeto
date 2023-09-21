package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String requisitos;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
