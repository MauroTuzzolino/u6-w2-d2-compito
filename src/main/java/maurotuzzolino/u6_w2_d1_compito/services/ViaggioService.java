package maurotuzzolino.u6_w2_d1_compito.services;

import maurotuzzolino.u6_w2_d1_compito.entities.Viaggio;
import maurotuzzolino.u6_w2_d1_compito.enums.StatoViaggio;
import maurotuzzolino.u6_w2_d1_compito.exceptions.NotFoundException;
import maurotuzzolino.u6_w2_d1_compito.repositories.ViaggioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Page<Viaggio> getAll(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio getById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con id " + id));
    }

    public Viaggio create(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio update(Long id, Viaggio updated) {
        Viaggio existing = getById(id);
        existing.setDestinazione(updated.getDestinazione());
        existing.setData(updated.getData());
        existing.setStato(updated.getStato());
        return viaggioRepository.save(existing);
    }

    public void delete(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new NotFoundException("Viaggio non trovato con id " + id);
        }
        viaggioRepository.deleteById(id);
    }

    public Viaggio cambiaStato(Long id, StatoViaggio nuovoStato) {
        Viaggio viaggio = getById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}