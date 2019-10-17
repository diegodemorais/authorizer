package nubank.validator;

import nubank.exception.AccountAlreadyInitializedException;
import nubank.model.Account;
import nubank.model.Base;

public class AccountValidator implements BusinessValidator{

    @Override
    public void validate(Base model) throws Exception {
        Account account = (Account)model;
        if (account.getActiveCard() != null) {
            throw new AccountAlreadyInitializedException("account-already-initialize");
        }
    }
}
