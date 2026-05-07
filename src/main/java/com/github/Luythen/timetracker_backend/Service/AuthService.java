package com.github.Luythen.timetracker_backend.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.Luythen.timetracker_backend.Dto.LoginDto;
import com.github.Luythen.timetracker_backend.Model.UserModel;
import com.github.Luythen.timetracker_backend.Repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final JWTService JWTService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailServiceImpl userDetailServiceImpl;

    AuthService(UserDetailServiceImpl userDetailServiceImpl, PasswordEncoder passwordEncoder, JWTService JWTService, UserRepository userRepository) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.JWTService = JWTService;
        this.userRepository = userRepository;
    }

    public String loginUser (LoginDto user) throws Exception {
        UserModel userModel = (UserModel) userDetailServiceImpl.loadUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), userModel.getPassword())) {
            throw new Exception("Password dosent match");
        }

        return JWTService.generateToken(userModel);
    }

    public void registerUser (UserModel user) throws Exception {
        if (user.getEmail().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty()) throw new Exception("Field or Fields cannot be empty");
        try {
            String passwrd = user.getPassword();
            user.setPassword(passwordEncoder.encode(passwrd));
            user.setRole("User");
            userRepository.insert(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
