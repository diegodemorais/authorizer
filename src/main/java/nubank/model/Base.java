package nubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nubank.exception.BusinessException;
import nubank.validator.AccountValidator;
import nubank.validator.BusinessValidator;
import nubank.validator.TransactionValidator;

public class Base {

    @JsonSerialize
    private String[] violations;
    @JsonIgnore
    private BusinessValidator validator;

    public Base() {
        this.initilizeViolations();
    }

    public String[] getViolations() {
        return violations;
    }

    void setViolations(String violations) {
        this.violations = new String[]{violations};
    }

    void initilizeViolations() {
        this.violations = new String[]{};
    }

    boolean validate() {
        try {
            if (this instanceof Account) {
                this.performValidate(AccountValidator.AccountAlreadyInitialized);
            } else {
                this.performValidate(TransactionValidator.TestTest);
            }
            this.initilizeViolations();
        } catch (BusinessException e) {
            this.setViolations(e.getMessage());
            return false;
        }
        return true;
    }

    private void performValidate(BusinessValidator validator) throws BusinessException {
        if (validator instanceof AccountValidator) {
            validator.validate((Account)this);
        } else {
            validator.validate((Transaction)this);
        }
    }
}
