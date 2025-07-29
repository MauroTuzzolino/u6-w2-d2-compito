package maurotuzzolino.u6_w2_d1_compito.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ViaggioPayload {

    @NotBlank
    private String destinazione;

    @NotNull
    private LocalDate data;

    @NotBlank
    private String stato;

    public ViaggioPayload(String destinazione, LocalDate data, String stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "ViaggioPayload{" +
                "destinazione='" + destinazione + '\'' +
                ", data=" + data +
                ", stato='" + stato + '\'' +
                '}';
    }
}
