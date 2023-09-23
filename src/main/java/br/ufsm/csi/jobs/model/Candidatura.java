package br.ufsm.csi.jobs.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario user;
    @ManyToOne
    private Vaga vaga;

}
