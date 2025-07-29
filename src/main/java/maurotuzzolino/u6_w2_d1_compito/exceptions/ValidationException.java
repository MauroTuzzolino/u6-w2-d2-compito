package maurotuzzolino.u6_w2_d1_compito.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(List<String> errors) {
        super("Errore di validazione");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
