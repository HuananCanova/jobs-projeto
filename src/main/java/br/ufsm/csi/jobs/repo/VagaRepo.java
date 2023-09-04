package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.TipoContrato;
import br.ufsm.csi.jobs.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepo extends JpaRepository<Vaga, Long> {
    List<Vaga> findVagaByTipoContrato(TipoContrato tipoContrato);
    List<Vaga> findByCidade(String cidade);

    void deleteByEmpresaId(Long id);
}
