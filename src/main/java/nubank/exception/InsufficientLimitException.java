package nubank.exception;

public class InsufficientLimitException extends BusinessException {

    public InsufficientLimitException(String message) {
        super(message);
    }

}
