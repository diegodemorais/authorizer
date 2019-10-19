/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
