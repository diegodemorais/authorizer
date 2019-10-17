package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.model.Account;
import nubank.model.Base;

public interface BusinessValidator {

    void validate(Base model) throws Exception;
}
