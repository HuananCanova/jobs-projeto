package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.infra.CandidaturaNotFoundException;
import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.repo.CandidaturaRepo;
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

    public Candidatura getCandidaturaById(Long id) throws CandidaturaNotFoundException {
        Optional<Candidatura> optionalCandidatura = candidaturaRepo.findById(id);
        if (optionalCandidatura.isPresent()) {
            return optionalCandidatura.get();
        } else {
            // Lógica para lidar com candidatura não encontrada
            throw new CandidaturaNotFoundException("Candidatura com ID " + id + " não encontrada");
        }
    }


    public void deleteCandidatura(Long id) {
        candidaturaRepo.deleteById(id);
    }
}
