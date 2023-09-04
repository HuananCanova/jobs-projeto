package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.repo.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepo empresaRepo;

    @Autowired
    public EmpresaService(EmpresaRepo empresaRepo) {
        this.empresaRepo = empresaRepo;
    }

    public Optional<Empresa> getEmpresaById(Long id) {
        return empresaRepo.findById(id);
    }

    public Optional<Empresa> findEmpresaByRazaoSocial(String razaoSocial) {
        return empresaRepo.findByRazaoSocial(razaoSocial);
    }

    public List<Empresa> findAllEmpresas() {
        return empresaRepo.findAll();
    }
}
