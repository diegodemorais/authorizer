package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.model.Account;

public enum AccountValidator implements IAccountValidator {

    AccountAlreadyInitialized {
        public void validate(Account account) throws AccountAlreadyInitializedException {
            if (account.getActiveCard() != null) {
                throw new AccountAlreadyInitializedException("account-already-initialized");
            }
        }
    }
}
