package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
