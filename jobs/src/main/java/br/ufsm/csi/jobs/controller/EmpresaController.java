package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //get by id
    @GetMapping("/{id}")
    Optional<Empresa> getEmpresaById(@PathVariable Long id){
        return empresaService.getEmpresaById(id);
    }


    //get by nome
    @GetMapping("/getByRazaoSocial")
    public Optional<Empresa> getEmpresaByRazaoSocial(@RequestParam("razaoSocial") String razaoSocial) {
        return empresaService.findEmpresaByRazaoSocial(razaoSocial);
    }

    @GetMapping("/list")
    List<Empresa> getAllEmpresas(){
        return empresaService.findAllEmpresas();
    }

}
