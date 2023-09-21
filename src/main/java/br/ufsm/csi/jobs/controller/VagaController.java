package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.infra.VagaNotFoundException;
import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Vaga> createVaga(@RequestBody @Valid Vaga vaga, UriComponentsBuilder uriBuilder) {
        vagaService.saveVaga(vaga);
        URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vaga.getId()).toUri();
        return ResponseEntity.created(uri).body(vaga);
    }


    @GetMapping
    public ResponseEntity<List<Vaga>> getAllVagas() {
        List<Vaga> vagas = vagaService.listarVagas();
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        Optional<Vaga> vaga = vagaService.getVagaById(id);
        return ResponseEntity.ok(vaga.orElseThrow());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) throws VagaNotFoundException {
        vagaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
