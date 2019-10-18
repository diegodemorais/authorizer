package nubank.validator;

import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Transaction;

public interface ITransactionValidator {
    void validate(Account account, Transaction transaction) throws BusinessException;
}
