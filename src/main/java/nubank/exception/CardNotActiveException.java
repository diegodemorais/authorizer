/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class CardNotActiveException extends BusinessException {

    public CardNotActiveException(String message) {
        super(message);
    }

}
