package maurotuzzolino.u6_w2_d1_compito.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PrenotazionePayload {

    @NotNull
    private Long dipendenteId;

    @NotNull
    private Long viaggioId;

    @NotNull
    private LocalDate dataRichiesta;

    private String note;

    public PrenotazionePayload(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note) {
        this.dipendenteId = dipendenteId;
        this.viaggioId = viaggioId;
        this.dataRichiesta = dataRichiesta;
        this.note = note;
    }

    public Long getDipendenteId() {
        return dipendenteId;
    }

    public void setDipendenteId(Long dipendenteId) {
        this.dipendenteId = dipendenteId;
    }

    public Long getViaggioId() {
        return viaggioId;
    }

    public void setViaggioId(Long viaggioId) {
        this.viaggioId = viaggioId;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PrenotazionePayload{" +
                "dipendenteId=" + dipendenteId +
                ", viaggioId=" + viaggioId +
                ", dataRichiesta=" + dataRichiesta +
                ", note='" + note + '\'' +
                '}';
    }
}