/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.exception;

public class HighFrequencySmallIntervalException extends BusinessException {

    public HighFrequencySmallIntervalException(String message) {
        super(message);
    }

}
