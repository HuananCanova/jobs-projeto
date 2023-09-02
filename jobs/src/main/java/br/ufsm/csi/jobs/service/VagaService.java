package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.repo.VagaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaService {
    private final VagaRepo vagaRepo;
    @Autowired
    public VagaService(VagaRepo vagaRepo) {
        this.vagaRepo = vagaRepo;
    }
}
