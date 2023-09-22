package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.dto.UserDTO;
import br.ufsm.csi.jobs.infra.UserNotFoundException;
import br.ufsm.csi.jobs.model.User;
import br.ufsm.csi.jobs.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public void createUser(User user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        user.getRoles().add("ROLE_USER");
        userRepo.save(user);
    }

    public List<User> listUsers() {
        return userRepo.findAll();
    }

    public List<UserDTO> findALlUsers(){
        return this.userRepo.findAll().stream().map(UserDTO::new).toList();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public UserDTO findUser(Long id){
        User user = this.userRepo.getReferenceById(id);
        return new UserDTO(user);
    }


    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new UserNotFoundException("User com ID " + id + " não encontrado");
        }
    }
}
