package maurotuzzolino.u6_w2_d1_compito.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.exceptions.UnauthorizedException;
import maurotuzzolino.u6_w2_d1_compito.services.DipendenteService;
import maurotuzzolino.u6_w2_d1_compito.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Recupero header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Verifico presenza e formato Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization Header nel formato corretto!");

        // 3. Estraggo il token togliendo il prefisso Bearer
        String accessToken = authHeader.replace("Bearer ", "");

        // 4. Verifico validità del token
        jwtTools.verifyToken(accessToken);

        // 5. Ottengo l'ID dal token
        Long dipendenteId = jwtTools.getIdFromToken(accessToken);

        // 6. Recupero il dipendente dal DB
        Dipendente dipendente = dipendenteService.getById(dipendenteId);

        // 7. Creo l'autenticazione e la inserisco nel contesto
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                dipendente, null, Collections.emptyList()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 8. Continuo la catena dei filtri
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/auth/login") || path.equals("/auth/register");
    }
}
