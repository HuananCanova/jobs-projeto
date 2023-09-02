package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "vagas")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;

    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "tipocontrato")
    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;

}
