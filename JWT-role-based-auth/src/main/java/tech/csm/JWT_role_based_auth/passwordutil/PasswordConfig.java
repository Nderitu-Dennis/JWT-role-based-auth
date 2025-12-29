package tech.csm.JWT_role_based_auth.passwordutil;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        //for {Bcrypt}
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
