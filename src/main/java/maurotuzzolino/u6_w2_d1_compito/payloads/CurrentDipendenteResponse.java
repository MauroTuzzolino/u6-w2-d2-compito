package maurotuzzolino.u6_w2_d1_compito.payloads;

public class CurrentDipendenteResponse {
    private Long id;
    private String email;
    private String username;

    public CurrentDipendenteResponse(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
