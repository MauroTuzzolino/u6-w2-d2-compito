package maurotuzzolino.u6_w2_d1_compito.services;

import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.exceptions.NotFoundException;
import maurotuzzolino.u6_w2_d1_compito.payloads.NewDipendenteRequest;
import maurotuzzolino.u6_w2_d1_compito.repositories.DipendenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public Page<Dipendente> getAll(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id " + id));
    }

    public Dipendente create(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente update(Long id, Dipendente updated) {
        Dipendente existing = getById(id);
        existing.setUsername(updated.getUsername());
        existing.setNome(updated.getNome());
        existing.setCognome(updated.getCognome());
        existing.setEmail(updated.getEmail());
        existing.setImmagineProfilo(updated.getImmagineProfilo());
        return dipendenteRepository.save(existing);
    }

    public void delete(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new NotFoundException("Dipendente non trovato con id " + id);
        }
        dipendenteRepository.deleteById(id);
    }

    public Dipendente findByEmail(String email) {
        return dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Dipendente con email " + email + " non trovato"));
    }

    public Dipendente save(NewDipendenteRequest body) {
        Dipendente nuovo = new Dipendente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail(), body.getPassword(), body.getImmagineProfilo());
        nuovo.setEmail(body.getEmail());
        nuovo.setPassword(body.getPassword());

        return dipendenteRepository.save(nuovo);
    }
}