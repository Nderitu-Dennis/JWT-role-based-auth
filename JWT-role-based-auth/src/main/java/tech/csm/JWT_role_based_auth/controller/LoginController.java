package tech.csm.JWT_role_based_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tech.csm.JWT_role_based_auth.jwt.JwtUtil;
import tech.csm.JWT_role_based_auth.dto.LoginRequest;
import tech.csm.JWT_role_based_auth.entity.Role;
import tech.csm.JWT_role_based_auth.entity.User;
import tech.csm.JWT_role_based_auth.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        System.out.println("Login endpoint hit!");

// validate username/password (DB)
        User user = userRepo.findByUsername(req.getUsername()).orElseThrow();

        System.out.println("**user " + user);

         List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }

//        generate JWT
        String token = jwtUtil.generateToken(user.getUsername(), roles);
        if(user.getUsername().isEmpty()){
            System.out.println("** user is empty");
        }
        System.out.println("token: " + token);
        System.out.println("username: " + user.getUsername());

//        RETURN TOKEN
        return ResponseEntity.ok(Map.of("token", token));
    }
}
