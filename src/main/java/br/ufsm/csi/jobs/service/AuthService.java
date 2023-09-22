package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.User;
import br.ufsm.csi.jobs.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userRepo.findByEmail(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }else{
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(usuario.getEmail()).password(usuario.getSenha())

                    .build();
            return userDetails;
        }
    }
}
