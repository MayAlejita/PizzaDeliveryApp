package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;
    public AppUserDetailsService(UserRepository userRepo) {this.userRepo = userRepo;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found for:" + username));
    }
}
