package edu.miu.cs.cs489.pizzadeliveryapp.repository;

import edu.miu.cs.cs489.pizzadeliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
