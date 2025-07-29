package maurotuzzolino.u6_w2_d1_compito.controllers;

import jakarta.validation.Valid;
import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.payloads.DipendentePayload;
import maurotuzzolino.u6_w2_d1_compito.services.DipendenteService;
import maurotuzzolino.u6_w2_d1_compito.services.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    private final ImageService imageService;

    public DipendenteController(DipendenteService dipendenteService, ImageService imageService) {
        this.dipendenteService = dipendenteService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Page<Dipendente>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(dipendenteService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Dipendente> create(@RequestBody @Valid DipendentePayload payload) {
        Dipendente dip = new Dipendente(
                payload.getUsername(),
                payload.getNome(),
                payload.getCognome(),
                payload.getEmail(),
                payload.getPassword(),
                payload.getImmagineProfilo()
        );
        return ResponseEntity.ok(dipendenteService.create(dip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> update(@PathVariable Long id, @RequestBody @Valid DipendentePayload payload) {
        Dipendente dip = new Dipendente(
                payload.getUsername(),
                payload.getNome(),
                payload.getCognome(),
                payload.getEmail(),
                payload.getPassword(),
                payload.getImmagineProfilo()
        );
        return ResponseEntity.ok(dipendenteService.update(id, dip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dipendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<String> uploadImmagineProfilo(@PathVariable Long id,
                                                        @RequestParam("file") MultipartFile file) throws IOException {
        Dipendente dipendente = dipendenteService.getById(id);
        String imageUrl = imageService.uploadImage(file);
        dipendente.setImmagineProfilo(imageUrl);
        dipendenteService.update(id, dipendente);
        return ResponseEntity.ok(imageUrl);
    }
}
