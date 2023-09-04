package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.dto.UserDTO;
import br.ufsm.csi.jobs.error.UserNotFoundException;
import br.ufsm.csi.jobs.model.User;
import br.ufsm.csi.jobs.repo.UserRepo;
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

    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setSenha(userDTO.getSenha());
        user.setNome(userDTO.getNome());

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
