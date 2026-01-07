package com.studies.api.features.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuth implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUserEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return user;
    }
}
