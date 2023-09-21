package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.infra.CandidaturaNotFoundException;
import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;

    @PostMapping
    public ResponseEntity<Candidatura> createCandidatura(@RequestBody @Valid Candidatura candidatura, UriComponentsBuilder uriBuilder) {
        candidaturaService.createCandidatura(candidatura);
        URI uri = uriBuilder.path("/candidatura/{id}").buildAndExpand(candidatura.getId()).toUri();
        return ResponseEntity.created(uri).body(candidatura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidatura> getCandidaturaById(@PathVariable Long id) {
        try {
            Candidatura candidatura = candidaturaService.getCandidaturaById(id);
            return ResponseEntity.ok(candidatura);
        } catch (CandidaturaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Candidatura>> getAllCandidaturas() {
        List<Candidatura> candidaturas = candidaturaService.getAllCandidaturas();
        return ResponseEntity.ok(candidaturas);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidatura(@PathVariable Long id) {
        candidaturaService.deleteCandidatura(id);
        return ResponseEntity.noContent().build();
    }
}
