package nubank.exception;

public class AccountAlreadyInitializedException extends BusinessException {

    public AccountAlreadyInitializedException(String message) {
        super(message);
    }

}
