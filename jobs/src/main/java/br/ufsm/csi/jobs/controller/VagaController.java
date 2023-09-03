package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.error.VagaNotFoundException;
import br.ufsm.csi.jobs.model.TipoContrato;
import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    private final VagaService vagaService;

    @Autowired
    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        try {
            Optional<Vaga> vaga = vagaService.getVagaById(id);
            return ResponseEntity.ok(vaga.orElseThrow(VagaNotFoundException::new));
        } catch (VagaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vaga>> listVaga() {
        List<Vaga> vagas = vagaService.listarVagas();
        return ResponseEntity.ok(vagas);
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

    @PostMapping
    public ResponseEntity<String> criarVaga(@RequestBody Vaga vaga) {
        vagaService.saveVaga(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vaga criada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) throws VagaNotFoundException {
        vagaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
