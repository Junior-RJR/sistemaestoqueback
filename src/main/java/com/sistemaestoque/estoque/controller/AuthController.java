package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.dto.LoginRequest;
import com.sistemaestoque.estoque.dto.ResetPasswordRequest;
import com.sistemaestoque.estoque.model.PasswordResetToken;
import com.sistemaestoque.estoque.repository.PasswordResetTokenRepository;
import com.sistemaestoque.estoque.repository.UserRepository;
import com.sistemaestoque.estoque.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // A lógica de login com Spring Security vai processar a autenticação.
        // Se a requisição chegar aqui, significa que a autenticação na sessão foi bem-sucedida.
        // O `LoginRequest` também é um DTO que está correto no seu código.
        return ResponseEntity.ok(Collections.singletonMap("message", "Login bem-sucedido!"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        Optional<User> userOptional = userRepository.findByUsername(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken(token, user);
            passwordResetTokenRepository.save(resetToken);
            System.out.println("Token de redefinição de senha gerado para " + user.getUsername() + ": " + token);
        }
        return ResponseEntity.ok("Se o e-mail estiver cadastrado, um link de redefinição de senha será enviado.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository.findByToken(request.getToken());
        if (tokenOptional.isPresent() && tokenOptional.get().getExpiryDate().isAfter(java.time.LocalDateTime.now())) {
            PasswordResetToken resetToken = tokenOptional.get();
            User user = resetToken.getUser();
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            passwordResetTokenRepository.delete(resetToken);
            return ResponseEntity.ok("Senha redefinida com sucesso!");
        }
        return ResponseEntity.badRequest().body("Token inválido ou expirado.");
    }
}