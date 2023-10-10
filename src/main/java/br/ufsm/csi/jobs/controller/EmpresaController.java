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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody @Valid Empresa novaEmpresa) {
        Optional<Empresa> empresaExistente = empresaService.getEmpresaById(id);

        if (empresaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empresa empresa = empresaExistente.get();

        // Atualize os campos da empresa existente com os dados da nova empresa
        empresa.setRazaoSocial(novaEmpresa.getRazaoSocial());
        empresa.setCNPJ(novaEmpresa.getCNPJ());
        empresa.setEmail(novaEmpresa.getEmail());
        // Atualize outros campos conforme necessário

        empresaService.updateEmpresa(empresa); // Método para salvar a empresa atualizada

        return ResponseEntity.ok(empresa);
    }


    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<String> deleteEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.getEmpresaById(id);

        if (empresa.isPresent()) {
            String razaoSocial = empresa.get().getRazaoSocial();

            empresaService.deleteVagasByEmpresaId(id);
            empresaService.deleteEmpresaById(id);

            return ResponseEntity.ok("Empresa com ID " + id + " e razão social '" + razaoSocial + "' foi deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
