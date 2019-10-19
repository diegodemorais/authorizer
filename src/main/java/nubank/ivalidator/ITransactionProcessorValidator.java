/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.ivalidator;

import nubank.TransactionProcessor;
import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Transaction;

public interface ITransactionProcessorValidator {
    void validate(Account account, Transaction transaction, TransactionProcessor processor) throws BusinessException;
}
