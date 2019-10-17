package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Base;

public enum AccountValidator implements BusinessValidator {

    AccountAlreadyInitialized {
        @Override
        public void validate(Base base) throws AccountAlreadyInitializedException {
            Account account = (Account)base;
            if (account.getActiveCard() != null) {
                throw new AccountAlreadyInitializedException("account-already-initialized");
            }
        }
    }
}
