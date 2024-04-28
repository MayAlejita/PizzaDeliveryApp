package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import java.util.List;

public record UserRequest (
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        boolean enabled,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        List<RoleRequest> roles
) {
}
