package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.ecommerce.dto.response.RegisterResponse;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.RoleType;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        if (request.role() == RoleType.ADMIN) {
            user.setRole(RoleType.ADMIN);
        } else {
            user.setRole(RoleType.USER);
        }



        userRepository.save(user);

        // Tek bir rol döndürme
        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
    }


    @Override
    public void assignRole(Long userId, String role) {
        // Kullanıcıya rol atama işlemi
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(RoleType.valueOf(role.toUpperCase()));
        userRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        // Parola değiştirme işlemi
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email) {
        // Parola sıfırlama işlemi
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode("temporaryPassword"));  // Geçici parola
        userRepository.save(user);
    }
}
