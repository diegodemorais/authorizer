/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class DoubledTransactionException extends BusinessException {

    public DoubledTransactionException(String message) {
        super(message);
    }

}
