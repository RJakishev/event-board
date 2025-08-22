package ee.ruslan.event_board.service;

import ee.ruslan.event_board.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String adminEmail;
    private final String adminPassword;
    private final JwtService jwtService;

    public AuthService(
            @Value("${app.admin.email}") String adminEmail,
            @Value("${app.admin.password}") String adminPassword,
            JwtService jwtService) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.jwtService = jwtService;
    }

    public String authenticate(String email, String password) {
        if (adminEmail.equals(email) && adminPassword.equals(password)) {
            return jwtService.generateToken(email);
        }
        throw new SecurityException("Invalid credentials");
    }
}

