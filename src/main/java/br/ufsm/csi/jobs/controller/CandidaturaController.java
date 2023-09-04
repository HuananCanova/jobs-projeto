package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.error.CandidaturaNotFoundException;
import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;

    @PostMapping
    public ResponseEntity<Candidatura> createCandidatura(@RequestBody Candidatura candidatura) {
        Candidatura createdCandidatura = candidaturaService.createCandidatura(candidatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidatura);
    }

    @GetMapping
    public ResponseEntity<List<Candidatura>> getAllCandidaturas() {
        List<Candidatura> candidaturas = candidaturaService.getAllCandidaturas();
        return ResponseEntity.ok(candidaturas);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidatura(@PathVariable Long id) {
        candidaturaService.deleteCandidatura(id);
        return ResponseEntity.noContent().build();
    }
}
