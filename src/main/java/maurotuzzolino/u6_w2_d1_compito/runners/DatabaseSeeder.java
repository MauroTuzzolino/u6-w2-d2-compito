package maurotuzzolino.u6_w2_d1_compito.runners;

import maurotuzzolino.u6_w2_d1_compito.entities.Dipendente;
import maurotuzzolino.u6_w2_d1_compito.entities.Prenotazione;
import maurotuzzolino.u6_w2_d1_compito.entities.Viaggio;
import maurotuzzolino.u6_w2_d1_compito.enums.StatoViaggio;
import maurotuzzolino.u6_w2_d1_compito.repositories.DipendenteRepository;
import maurotuzzolino.u6_w2_d1_compito.repositories.PrenotazioneRepository;
import maurotuzzolino.u6_w2_d1_compito.repositories.ViaggioRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

//@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;
    private final PrenotazioneRepository prenotazioneRepository;


    public DatabaseSeeder(DipendenteRepository dipendenteRepository,
                          ViaggioRepository viaggioRepository,
                          PrenotazioneRepository prenotazioneRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
        this.prenotazioneRepository = prenotazioneRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Dipendenti
        Dipendente mario = new Dipendente("m.rossi", "Mario", "Rossi", "mario.rossi@email.com", "password123", null);
        Dipendente giulia = new Dipendente("g.bianchi", "Giulia", "Bianchi", "giulia.bianchi@email.com", "password456", null);
        Dipendente luigi = new Dipendente("l.verdi", "Luigi", "Verdi", "luigi.verdi@email.com", "password789", null);

        dipendenteRepository.save(mario);
        dipendenteRepository.save(giulia);
        dipendenteRepository.save(luigi);

        // Viaggi
        Viaggio milano = new Viaggio("Milano", LocalDate.now().plusDays(10), StatoViaggio.IN_PROGRAMMA);
        Viaggio londra = new Viaggio("Londra", LocalDate.now().minusDays(15), StatoViaggio.COMPLETATO);

        viaggioRepository.save(milano);
        viaggioRepository.save(londra);

        // Prenotazioni
        Prenotazione p1 = new Prenotazione(LocalDate.now(), "Finestrino, hotel centrale", mario, milano);
        Prenotazione p2 = new Prenotazione(LocalDate.now().minusDays(16), "Business class", giulia, londra);

        prenotazioneRepository.save(p1);
        prenotazioneRepository.save(p2);

        System.out.println("✅ Database inizializzato con dati di esempio.");
    }
}