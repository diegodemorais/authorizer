package nubank.exception;

public class DoubledTransactionException extends Exception {

    public DoubledTransactionException(String message) {
        super(message);
    }

}
