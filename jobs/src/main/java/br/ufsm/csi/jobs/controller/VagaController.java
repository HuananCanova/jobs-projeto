package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.VagaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaController {


    private final VagaService vagaService;
    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }
    @GetMapping("/list")
    public List<Vaga> listVaga(){
        return vagaService.listarVagas();
    }

}
