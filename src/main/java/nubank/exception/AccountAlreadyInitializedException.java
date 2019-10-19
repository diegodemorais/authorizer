/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class AccountAlreadyInitializedException extends BusinessException {

    public AccountAlreadyInitializedException(String message) {
        super(message);
    }

}
