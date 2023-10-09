package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.repo.CandidaturaRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {
    private final CandidaturaRepo candidaturaRepo;

    @Autowired
    public CandidaturaService(CandidaturaRepo candidaturaRepo) {
        this.candidaturaRepo = candidaturaRepo;
    }

    public Candidatura createCandidatura(Candidatura candidatura) {
        return candidaturaRepo.save(candidatura);
    }

    public List<Candidatura> getAllCandidaturas() {
        return candidaturaRepo.findAll();
    }

    public Optional<Candidatura> getCandidaturaById(Long id) {
        return candidaturaRepo.findById(id)
                .map(candidatura -> {
                    Hibernate.initialize(candidatura.getUser());
                    Hibernate.initialize(candidatura.getVaga());
                    return candidatura;
                });
    }




    public void deleteCandidatura(Long id) {
        candidaturaRepo.deleteById(id);
    }
}
