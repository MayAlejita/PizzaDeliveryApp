package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.UserRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.UserResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.UserNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Role;
import edu.miu.cs.cs489.pizzadeliveryapp.model.User;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.RoleRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.UserRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponse(
                        u.getUserId(),
                        u.getUsername(), u.getFirstName(),
                        u.getLastName(), u.getEmail(),
                        u.isEnabled(), u.isAccountNonExpired(),
                        u.isAccountNonLocked(), u.isCredentialsNonExpired()
                ))
                .toList();
    }

    @Override
    public UserResponse getUserById(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(String.format("Error: User with Id, %d, is not found", userId));
        }
        return new UserResponse(user.getUserId(),
                user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.isEnabled(), user.isAccountNonExpired(),
                user.isAccountNonLocked(), user.isCredentialsNonExpired()
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        List<Role> roles;
        if(userRequest.roles() == null || userRequest.roles().isEmpty()) {
            roles = List.of(roleRepository.findByRoleName("ROLE_CUSTOMER").orElse(null));
        }
        else{
            roles = userRequest.roles()
                    .stream()
                    .map(r -> roleRepository.findByRoleName(r.roleName()).orElse(null))
                    .toList();
        }

        User user = new User(null,
                userRequest.username(), passwordEncoder.encode(userRequest.password()),
                userRequest.firstName(), userRequest.lastName(),
                userRequest.email(), userRequest.credentialsNonExpired(),
                userRequest.accountNonExpired(), userRequest.enabled(),
                userRequest.accountNonLocked(), roles);
        User saveUser = userRepository.save(user);
        return new UserResponse(saveUser.getUserId(),
                saveUser.getUsername(), saveUser.getFirstName(),
                saveUser.getLastName(), saveUser.getEmail(),
                saveUser.isEnabled(), saveUser.isAccountNonExpired(),
                saveUser.isAccountNonLocked(), saveUser.isCredentialsNonExpired()
        );
    }

    @Override
    public UserResponse updateUserById(Integer userId, UserRequest userRequest) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(String.format("Error: User with Id, %d, is not found", userId));
        }

        if(userRequest.roles() != null && !userRequest.roles().isEmpty()) {
            List<Role> roles = userRequest.roles()
                    .stream()
                    .map(r -> roleRepository.findByRoleName(r.roleName()).orElse(null))
                    .toList();
            user.setRoles(roles);
        }

        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setAccountNonExpired(userRequest.accountNonExpired());
        user.setAccountNonLocked(userRequest.accountNonLocked());
        user.setCredentialsNonExpired(userRequest.credentialsNonExpired());
        user.setEnabled(userRequest.enabled());
        User upUser = userRepository.save(user);
        return new UserResponse(upUser.getUserId(),
                upUser.getUsername(), upUser.getFirstName(),
                upUser.getLastName(), upUser.getEmail(),
                upUser.isEnabled(), upUser.isAccountNonExpired(),
                upUser.isAccountNonLocked(), upUser.isCredentialsNonExpired()
        );
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }
}
