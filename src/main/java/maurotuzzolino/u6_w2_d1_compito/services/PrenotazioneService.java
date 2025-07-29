package maurotuzzolino.u6_w2_d1_compito.services;

import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.entities.Prenotazione;
import maurotuzzolino.u6_w2_d1_compito.entities.Viaggio;
import maurotuzzolino.u6_w2_d1_compito.exceptions.BadRequestException;
import maurotuzzolino.u6_w2_d1_compito.exceptions.NotFoundException;
import maurotuzzolino.u6_w2_d1_compito.repositories.PrenotazioneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               DipendenteService dipendenteService,
                               ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }

    public Page<Prenotazione> getAll(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione getById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata con id " + id));
    }

    public Prenotazione creaPrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note) {
        Dipendente dipendente = dipendenteService.getById(dipendenteId);
        Viaggio viaggio = viaggioService.getById(viaggioId);

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, dataRichiesta)) {
            throw new BadRequestException("Dipendente già prenotato per questa data");
        }

        Prenotazione prenotazione = new Prenotazione(dataRichiesta, note, dipendente, viaggio);
        return prenotazioneRepository.save(prenotazione);
    }

    public void delete(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new NotFoundException("Prenotazione non trovata con id " + id);
        }
        prenotazioneRepository.deleteById(id);
    }
}