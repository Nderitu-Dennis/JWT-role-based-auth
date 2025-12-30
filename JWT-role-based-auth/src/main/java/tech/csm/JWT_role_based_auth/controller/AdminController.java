package tech.csm.JWT_role_based_auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*") //fix CORS error in HTML
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "ADMIN dashboard";
    }
}
