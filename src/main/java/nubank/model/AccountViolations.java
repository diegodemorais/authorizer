package nubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nubank.exception.BusinessException;
import nubank.validator.AccountValidator;
import nubank.IValidator.IAccountValidator;

public class AccountViolations {
    private Account account;
    private String[] violations;

    public AccountViolations(){
    }

    public Account getAccount() {
        return account;
    }

    public String[] getViolations() {
        return violations;
    }

    public void setViolations(String violations) {
        this.violations = new String[]{violations};
    }

    public void resetViolations() {
        this.violations = new String[]{};
    }

    private void validate(IAccountValidator validator) {
        try {
            validator.validate(this.getAccount());
        } catch (BusinessException e) {
            this.setViolations(e.getMessage());
        }
    }

    public void createAccount(boolean activeCard, int availableLimit) {
        this.resetViolations();
        this.validate(AccountValidator.AccountAlreadyInitialized);
        if (isValid()) {
            setAccount(activeCard,availableLimit);
        }
    }

    private void setAccount(boolean activeCard, int availableLimit) {
        this.account = new Account(activeCard, availableLimit);
        this.resetViolations();
    }



    @JsonIgnore
    public boolean isValid(){
        return this.getViolations().length == 0;
    }


}
