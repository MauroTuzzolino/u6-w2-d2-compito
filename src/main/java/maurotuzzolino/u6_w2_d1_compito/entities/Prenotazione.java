package maurotuzzolino.u6_w2_d1_compito.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dipendente_id", "data_richiesta"})
})
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    public Prenotazione() {
    }

    public Prenotazione(LocalDate dataRichiesta, String note, Dipendente dipendente, Viaggio viaggio) {
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.dipendente = dipendente;
        this.viaggio = viaggio;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public String getNote() {
        return note;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataRichiesta=" + dataRichiesta +
                ", note='" + note + '\'' +
                ", dipendente=" + dipendente +
                ", viaggio=" + viaggio +
                '}';
    }
}