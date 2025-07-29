package maurotuzzolino.u6_w2_d1_compito.controllers;

import jakarta.validation.Valid;
import maurotuzzolino.u6_w2_d1_compito.entities.Prenotazione;
import maurotuzzolino.u6_w2_d1_compito.payloads.PrenotazionePayload;
import maurotuzzolino.u6_w2_d1_compito.services.PrenotazioneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @GetMapping
    public ResponseEntity<Page<Prenotazione>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(prenotazioneService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable Long id) {
        return prenotazioneService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Prenotazione> prenota(@RequestBody @Valid PrenotazionePayload payload) {
        return ResponseEntity.ok(prenotazioneService.creaPrenotazione(
                payload.getDipendenteId(),
                payload.getViaggioId(),
                payload.getDataRichiesta(),
                payload.getNote()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}