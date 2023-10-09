package br.ufsm.csi.jobs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Adicione esta linha para carregar o usuário apenas quando necessário
    @JsonIgnoreProperties({"candidaturas"}) // Ignore a propriedade "candidaturas" no usuário para evitar uma referência cíclica
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY) // Adicione esta linha para carregar a vaga apenas quando necessário
    @JsonIgnoreProperties({"candidaturas"}) // Ignore a propriedade "candidaturas" na vaga para evitar uma referência cíclica
    private Vaga vaga;

    public Candidatura(Long id, Usuario user, Vaga vaga) {

    }
}