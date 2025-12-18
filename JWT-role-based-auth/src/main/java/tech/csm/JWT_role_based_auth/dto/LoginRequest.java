package tech.csm.JWT_role_based_auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
