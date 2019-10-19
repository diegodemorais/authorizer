/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.ivalidator;

import nubank.exception.BusinessException;
import nubank.model.Account;

public interface IAccountValidator {
    void validate(Account account) throws BusinessException;
}
