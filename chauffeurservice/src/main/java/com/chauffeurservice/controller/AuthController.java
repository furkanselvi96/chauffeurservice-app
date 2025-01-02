package com.chauffeurservice.controller;

import com.chauffeurservice.model.AppUser;
import com.chauffeurservice.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/sign-up")
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        // Kullanıcıyı veritabanına kaydet
        appUserRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String login(@RequestHeader("Authorization") String authorizationHeader) {
        // Header'dan kullanıcı adı ve şifreyi çıkar
        String auth = authorizationHeader.replace("Basic ", "");
        String decodedAuth = new String(Base64.getDecoder().decode(auth));
        String[] credentials = decodedAuth.split(":");

        String email = credentials[0];
        String password = credentials[1];

        // Kullanıcıyı doğrula
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Başarılı giriş sonrası kimlik doğrulama işlemini güvenlik bağlamına ekle
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Login successful";
    }
}

