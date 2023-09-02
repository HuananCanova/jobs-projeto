package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Localizacao;
import br.ufsm.csi.jobs.model.TipoContrato;
import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/searchByTipoContrato")
    public ResponseEntity<List<Vaga>> searchByTipoContrato(@RequestParam("tipoContrato") TipoContrato tipoContrato) {
        List<Vaga> vagas = vagaService.findByTipoContrato(tipoContrato);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/searchByCidade")
    public ResponseEntity<List<Vaga>> searchByCidade(@RequestParam("cidade") String cidade) {
        List<Vaga> vagas = vagaService.findByCidade(cidade);
        return ResponseEntity.ok(vagas);
    }

}
