package tech.csm.JWT_role_based_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import tech.csm.JWT_role_based_auth.jwt.JwtUtil;
import tech.csm.JWT_role_based_auth.dto.LoginRequest;
import tech.csm.JWT_role_based_auth.entity.Role;
import tech.csm.JWT_role_based_auth.entity.User;
import tech.csm.JWT_role_based_auth.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
// validate username/password (DB)
        User user = userRepo.findByUsername(req.getUsername()).orElseThrow();
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        String token = jwtUtil.generateToken(user.getUsername(), roles);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
