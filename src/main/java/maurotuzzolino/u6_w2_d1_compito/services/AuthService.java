// src/main/java/maurotuzzolino/u6_w2_d1_compito/services/AuthService.java
package maurotuzzolino.u6_w2_d1_compito.services;

import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.exceptions.UnauthorizedException;
import maurotuzzolino.u6_w2_d1_compito.payloads.LoginRequest;
import maurotuzzolino.u6_w2_d1_compito.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(LoginRequest body) {
        Dipendente dip = dipendenteService.findByEmail(body.email());
        if (dip == null || !dip.getPassword().equals(body.password())) {
            throw new UnauthorizedException("Credenziali non valide");
        }
        return jwtTools.createToken(dip);
    }
}