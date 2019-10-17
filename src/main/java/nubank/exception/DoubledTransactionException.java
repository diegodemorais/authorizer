package nubank.exception;

public class DoubledTransactionException extends BusinessException {

    public DoubledTransactionException(String message) {
        super(message);
    }

}
