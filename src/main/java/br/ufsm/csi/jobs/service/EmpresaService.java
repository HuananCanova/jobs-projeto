package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.error.EmpresaNotFoundException;
import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.repo.EmpresaRepo;
import br.ufsm.csi.jobs.repo.VagaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepo empresaRepo;
    private final VagaRepo vagaRepo;

    @Autowired
    public EmpresaService(EmpresaRepo empresaRepo, VagaRepo vagaRepo) {
        this.empresaRepo = empresaRepo;
        this.vagaRepo = vagaRepo;
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

    public void deleteEmpresaById(Long id) throws EmpresaNotFoundException {
        if (empresaRepo.existsById(id)) {
            empresaRepo.deleteById(id);
        } else {
            throw new EmpresaNotFoundException();
        }
    }

    @Transactional
    public void deleteVagasByEmpresaId(Long id) {
        vagaRepo.deleteByEmpresaId(id);
    }
}
