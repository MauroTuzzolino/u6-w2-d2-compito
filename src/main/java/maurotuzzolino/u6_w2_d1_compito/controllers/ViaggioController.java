package maurotuzzolino.u6_w2_d1_compito.controllers;

import jakarta.validation.Valid;
import maurotuzzolino.u6_w2_d1_compito.entities.Viaggio;
import maurotuzzolino.u6_w2_d1_compito.enums.StatoViaggio;
import maurotuzzolino.u6_w2_d1_compito.payloads.CambiaStatoPayload;
import maurotuzzolino.u6_w2_d1_compito.payloads.ViaggioPayload;
import maurotuzzolino.u6_w2_d1_compito.services.ViaggioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @GetMapping
    public ResponseEntity<Page<Viaggio>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(viaggioService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable Long id) {
        return viaggioService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Viaggio> create(@RequestBody @Valid ViaggioPayload payload) {
        Viaggio viaggio = new Viaggio(
                payload.getDestinazione(),
                payload.getData(),
                StatoViaggio.valueOf(payload.getStato().toUpperCase())
        );
        return ResponseEntity.ok(viaggioService.create(viaggio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> update(@PathVariable Long id, @RequestBody @Valid ViaggioPayload payload) {
        Viaggio viaggio = new Viaggio(
                payload.getDestinazione(),
                payload.getData(),
                StatoViaggio.valueOf(payload.getStato().toUpperCase())
        );
        return ResponseEntity.ok(viaggioService.update(id, viaggio));
    }

    @PatchMapping("/{id}/stato")
    public ResponseEntity<Viaggio> cambiaStato(@PathVariable Long id, @RequestBody @Valid CambiaStatoPayload payload) {
        StatoViaggio nuovoStato = StatoViaggio.valueOf(payload.getNuovoStato().toUpperCase());
        return ResponseEntity.ok(viaggioService.cambiaStato(id, nuovoStato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        viaggioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}