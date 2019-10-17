package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.exception.BusinessException;
import nubank.exception.InsufficientLimitException;
import nubank.model.Account;
import nubank.model.Base;
import nubank.model.Transaction;

public enum TransactionValidator implements BusinessValidator {

    TestTest {
        @Override
        public void validate(Base base) throws InsufficientLimitException {
            Transaction transaction = (Transaction) base;
            if (transaction.getAmount() < 0) {
                throw new InsufficientLimitException("testTest");
            }
        }
    }
}
