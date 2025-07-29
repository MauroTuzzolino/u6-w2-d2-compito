package maurotuzzolino.u6_w2_d1_compito.controllers;


import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.payloads.*;
import maurotuzzolino.u6_w2_d1_compito.services.AuthService;
import maurotuzzolino.u6_w2_d1_compito.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest body) {
        String token = authService.checkCredentialsAndGenerateToken(body);
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteResponse register(@RequestBody @Validated NewDipendenteRequest body) {
        Dipendente nuovo = dipendenteService.save(body);
        return new NewDipendenteResponse(nuovo.getId());
    }

    @GetMapping("/me")
    public CurrentDipendenteResponse getCurrentUser(Authentication authentication) {
        Dipendente current = (Dipendente) authentication.getPrincipal();
        return new CurrentDipendenteResponse(current.getId(), current.getEmail(), current.getUsername());
    }
}
