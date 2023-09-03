package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.error.VagaNotFoundException;
import br.ufsm.csi.jobs.model.TipoContrato;
import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.repo.VagaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    private final VagaRepo vagaRepo;

    @Autowired
    public VagaService(VagaRepo vagaRepo) {
        this.vagaRepo = vagaRepo;
    }

    public List<Vaga> listarVagas() {
        return vagaRepo.findAll();
    }

    public List<Vaga> findByTipoContrato(TipoContrato tipoContrato) {
        return vagaRepo.findVagaByTipoContrato(tipoContrato);
    }

    public List<Vaga> findByCidade(String cidade) {
        return vagaRepo.findByCidade(cidade);
    }

    public void saveVaga(Vaga vaga) {
        vagaRepo.save(vaga);
    }

    public void deleteById(Long id) throws VagaNotFoundException {
        if (vagaRepo.existsById(id)) {
            vagaRepo.deleteById(id);
        } else {
            throw new VagaNotFoundException();
        }
    }

    public Optional<Vaga> getVagaById(Long id) {
        return vagaRepo.findById(id);
    }
}
