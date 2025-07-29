package maurotuzzolino.u6_w2_d1_compito.payloads;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import maurotuzzolino.u6_w2_d1_compito.enums.RuoloDipendente;

public class NewDipendenteRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @Enumerated(EnumType.STRING)
    private RuoloDipendente ruolo;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String immagineProfilo;

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(String immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }

    public RuoloDipendente getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloDipendente ruolo) {
        this.ruolo = ruolo;
    }
}
