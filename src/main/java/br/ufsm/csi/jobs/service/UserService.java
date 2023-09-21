package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.infra.UserNotFoundException;
import br.ufsm.csi.jobs.model.User;
import br.ufsm.csi.jobs.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Transactional
    public void createUser(User user) {
        userRepo.save(user);
    }

    public List<User> listUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new UserNotFoundException("User com ID " + id + " não encontrado");
        }
    }
}
