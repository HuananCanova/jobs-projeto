package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Localizacao;
import br.ufsm.csi.jobs.model.TipoContrato;
import br.ufsm.csi.jobs.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepo extends JpaRepository<Vaga, Long> {
    List<Vaga> findVagaByTipoContrato(TipoContrato tipoContrato);

    List<Vaga> findVagaByCidade(String cidade);
}
