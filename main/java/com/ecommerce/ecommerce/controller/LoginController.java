package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.LoginRequest;
import com.ecommerce.ecommerce.dto.response.LoginResponse;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {

        System.out.println("Gelen Email: " + request.email());
        System.out.println("Gelen Password: " + request.password());

        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());


            if (passwordEncoder.matches(request.password(), userDetails.getPassword())) {
                // Authentication oluşturma burda
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // sessşon burda
                HttpSession session = httpServletRequest.getSession(true);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                System.out.println("Session ID: " + session.getId());

                // Kullanıcı bilgileri burda
                User user = userRepository.findByEmail(request.email())
                        .orElseThrow(() -> new RuntimeException("User not found"));


                LoginResponse response = new LoginResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole().name()
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(HttpServletRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>("Giriş yapan kullanıcı: " + authentication.getName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Oturum açılmamış", HttpStatus.UNAUTHORIZED);
        }
    }

}
