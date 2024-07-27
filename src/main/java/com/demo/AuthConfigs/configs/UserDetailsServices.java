package com.demo.AuthConfigs.configs;

import com.demo.AuthConfigs.models.User;
import com.demo.AuthConfigs.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    UserRepo userRepo ;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByEmail(email);
        System.out.println("User Details :"+user.toString());
        if (user.isPresent()) {
            User foundUser = user.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(foundUser.getEmail())
                    .password(foundUser.getPassword())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
