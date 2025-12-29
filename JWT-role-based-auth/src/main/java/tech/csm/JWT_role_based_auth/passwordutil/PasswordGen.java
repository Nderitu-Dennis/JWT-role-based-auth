package tech.csm.JWT_role_based_auth.passwordutil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGen {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      //  System.out.println(encoder.encode("admin12345"));

        BCryptPasswordEncoder decoder = new BCryptPasswordEncoder();
        System.out.println(decoder.matches("admin1234","$2a$10$9rs9dZryk9fPM/q3BwRf5.1s1tNs.cGdEeMXtFvUR6eUCh8E5trRe"));

    }
}
