package nubank.validator;

import nubank.TransactionProcessor;
import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Transaction;

public interface ITransactionValidator {
    void validate(Account account, Transaction transaction, TransactionProcessor processor) throws BusinessException;
}
