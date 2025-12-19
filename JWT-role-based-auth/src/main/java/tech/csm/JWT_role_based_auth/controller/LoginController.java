package tech.csm.JWT_role_based_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.csm.JWT_role_based_auth.jwt.JwtUtil;
import tech.csm.JWT_role_based_auth.dto.LoginRequest;
import tech.csm.JWT_role_based_auth.entity.Role;
import tech.csm.JWT_role_based_auth.entity.User;
import tech.csm.JWT_role_based_auth.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        // Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

// validate username/password (DB)
        User user = userRepo.findByUsername(req.getUsername()).orElseThrow();
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }

//        generate JWT
        String token = jwtUtil.generateToken(user.getUsername(), roles);

//        RETURN TOKEN
        return ResponseEntity.ok(Map.of("token", token));
    }
}
