package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.repo.CandidaturaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidaturaService {
    private final CandidaturaRepo candidaturaRepo;
    @Autowired

    public CandidaturaService(CandidaturaRepo candidaturaRepo) {
        this.candidaturaRepo = candidaturaRepo;
    }


}
