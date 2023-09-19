package br.ufsm.csi.jobs.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String complemento;
    private String bairro;
    @Size(min = 8, max = 9, message = "CEP invalido")
    private String cep;
    private String numero;
    private String cidade;
    private String uf;

}
