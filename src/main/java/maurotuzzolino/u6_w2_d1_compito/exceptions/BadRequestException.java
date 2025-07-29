package maurotuzzolino.u6_w2_d1_compito.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}