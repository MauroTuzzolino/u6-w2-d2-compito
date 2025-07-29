package maurotuzzolino.u6_w2_d1_compito.repositories;

import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByUsername(String username);

    Optional<Dipendente> findByEmail(String email);
    
    boolean existsByEmail(String email);
}