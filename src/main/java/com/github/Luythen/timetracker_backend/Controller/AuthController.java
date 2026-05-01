package com.github.Luythen.timetracker_backend.Controller;

import com.github.Luythen.timetracker_backend.Service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.timetracker_backend.Dto.LoginDto;
import com.github.Luythen.timetracker_backend.Model.UserModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginDto loginReq, HttpServletResponse response) { 
        try {
            String token = authService.loginUser(loginReq);
            Cookie cookie = new Cookie("Token", token);
            cookie.setMaxAge(86400);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return ResponseEntity.status(200).body("{'message': 'Login successful'}");
        } catch (Exception e) {
           return ResponseEntity.status(401).body("{'message': '" + e.getMessage() + "'}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UserModel registerReq) {
        try {
            authService.registerUser(registerReq);
            return ResponseEntity.status(200).body("{'message': 'Register new User successfully'}");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("{'message': '" + e.getMessage() + "'}");
        }
    }
    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal().getName() != null) {
            Cookie cookie = new Cookie("Token", "token");
            cookie.setMaxAge(0);
            cookie.setPath("/");

            response.addCookie(cookie);
        }
        
        return "entity";
    }
    

}
