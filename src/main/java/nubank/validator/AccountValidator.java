package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.model.Account;
import nubank.ivalidator.IAccountValidator;

public enum AccountValidator implements IAccountValidator {

    AccountAlreadyInitialized {
        public void validate(Account account) throws AccountAlreadyInitializedException {
            if (account != null) {
                throw new AccountAlreadyInitializedException("account-already-initialized");
            }
        }
    }
}
