package tech.csm.JWT_role_based_auth.repository;

import org.springframework.stereotype.Repository;
import tech.csm.JWT_role_based_auth.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo {
    Optional<User> findByUsername(String username);
}
