package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.CandidaturaService;
import br.ufsm.csi.jobs.service.UserService;
import br.ufsm.csi.jobs.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Candidatura> createCandidatura(@RequestBody @Valid Candidatura candidatura, UriComponentsBuilder uriBuilder) {
        candidaturaService.createCandidatura(candidatura);
        URI uri = uriBuilder.path("/candidatura/{id}").buildAndExpand(candidatura.getId()).toUri();
        return ResponseEntity.created(uri).body(candidaturaService.getCandidaturaById(candidatura.getId()).orElse(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candidatura>> getCandidaturaById(@PathVariable Long id){
        Optional<Candidatura> candidatura = candidaturaService.getCandidaturaById(id);
        return ResponseEntity.ok(candidatura);
    }



    @GetMapping
    public ResponseEntity<List<Candidatura>> getAllCandidaturas() {
        List<Candidatura> candidaturas = candidaturaService.getAllCandidaturas();
        return ResponseEntity.ok(candidaturas);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> deleteCandidatura(@PathVariable Long id) {
        Optional<Candidatura> candidatura = candidaturaService.getCandidaturaById(id);

        if (candidatura.isPresent()) {
            String descricaoVaga = candidatura.get().getVaga().getDescricao(); // Obtenha a descrição da vaga associada à candidatura

            candidaturaService.deleteCandidatura(id);

            return ResponseEntity.ok("Candidatura com ID " + id + " associada à vaga '" + descricaoVaga + "' foi deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
