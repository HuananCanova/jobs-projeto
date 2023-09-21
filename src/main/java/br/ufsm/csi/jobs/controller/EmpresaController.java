package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.error.EmpresaNotFoundException;
import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa){
        empresaService.createEmpresa(empresa);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.getEmpresaById(id);
        return ResponseEntity.ok(empresa.orElseThrow());
    }

    @GetMapping("/getByRazaoSocial")
    public ResponseEntity<Empresa> getEmpresaByRazaoSocial(@RequestParam("razaoSocial") String razaoSocial) {
        Optional<Empresa> empresa = empresaService.findEmpresaByRazaoSocial(razaoSocial);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAllEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) throws EmpresaNotFoundException {
        empresaService.deleteVagasByEmpresaId(id);
        empresaService.deleteEmpresaById(id);
        return ResponseEntity.noContent().build();
    }
}
