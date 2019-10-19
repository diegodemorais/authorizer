package nubank.IValidator;

import nubank.exception.BusinessException;
import nubank.model.Account;

public interface IAccountValidator {
    void validate(Account account) throws BusinessException;
}
