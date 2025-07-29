package maurotuzzolino.u6_w2_d1_compito.repositories;

import maurotuzzolino.u6_w2_d1_compito.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}