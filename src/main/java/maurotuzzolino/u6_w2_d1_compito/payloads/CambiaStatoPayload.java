package maurotuzzolino.u6_w2_d1_compito.payloads;

import jakarta.validation.constraints.NotBlank;

public class CambiaStatoPayload {

    @NotBlank
    private String nuovoStato;

    public CambiaStatoPayload(String nuovoStato) {
        this.nuovoStato = nuovoStato;
    }

    public String getNuovoStato() {

        return nuovoStato;
    }

    public void setNuovoStato(String nuovoStato) {
        this.nuovoStato = nuovoStato;
    }

    @Override
    public String toString() {
        return "CambiaStatoPayload{" +
                "nuovoStato='" + nuovoStato + '\'' +
                '}';
    }
}
