/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class InsufficientLimitException extends BusinessException {

    public InsufficientLimitException(String message) {
        super(message);
    }

}
