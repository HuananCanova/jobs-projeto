package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.service.EmpresaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody @Valid Empresa empresa, UriComponentsBuilder uriBuilder) {
        empresaService.createEmpresa(empresa);
        URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.getEmpresaById(id);
        return ResponseEntity.ok(empresa.get());
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAllEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/getByRazaoSocial")
    public ResponseEntity<Empresa> getEmpresaByRazaoSocial(@RequestParam("razaoSocial") String razaoSocial) {
        Optional<Empresa> empresa = empresaService.findEmpresaByRazaoSocial(razaoSocial);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.deleteVagasByEmpresaId(id);
        empresaService.deleteEmpresaById(id);
        return ResponseEntity.noContent().build();

}
}
