package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Base;

public interface BusinessValidator {
    void validate(Base base) throws BusinessException;
}
