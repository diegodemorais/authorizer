package nubank.validator;

import nubank.exception.CardNotActiveException;
import nubank.exception.InsufficientLimitException;
import nubank.model.Account;
import nubank.model.Transaction;

public enum TransactionValidator implements ITransactionValidator {

    InsufficientLimit {
        public void validate(Account account, Transaction transaction) throws InsufficientLimitException {
            if (account.getAvailableLimit() - transaction.getAmount() < 0) {
                throw new InsufficientLimitException("insufficient-limit");
            }
        }
    },
    CardNotActive {
        public void validate(Account account, Transaction transaction) throws CardNotActiveException {
            if (!account.getActiveCard()) {
                throw new CardNotActiveException("card-not-active");
            }
        }
    };
}
