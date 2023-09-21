package br.ufsm.csi.jobs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String complemento;
    private String bairro;
    @Size(min = 8, max = 9, message = "CEP inválido")
    @NotNull
    private String cep;
    private String numero;
    @NotNull
    private String cidade;
    @Size(max = 2, message = "UF inválido")
    @NotNull
    private String uf;
}

