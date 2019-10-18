package nubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import nubank.exception.BusinessException;
import nubank.validator.AccountValidator;
import nubank.validator.IAccountValidator;

@JsonRootName("account")
@JsonPropertyOrder({"activeCard", "availableLimit", "violations"})
public class Account {
    private Boolean activeCard;
    private Integer availableLimit;
    private String[] violations;

    public Account() {
        this.resetViolations();
        this.setAccount(null, 0);
    }

    public Account createAccount(boolean activeCard, int availableLimit) {
        if (this.validate(AccountValidator.AccountAlreadyInitialized))  {
            this.setAccount(activeCard, availableLimit);
        }
        return this;
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

    public Boolean getActiveCard() {
        return activeCard;
    }

    public Integer getAvailableLimit() {
        return availableLimit;
    }

    public void applyDebit(Integer amount) {
        if (amount != null) {
            this.availableLimit -= amount;
            this.resetViolations();
        }
    }

    private void setAccount(Boolean activeCard, int availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
    }

    boolean validate(IAccountValidator validator) {
        try {
            validator.validate(this);
            this.resetViolations();
        } catch (BusinessException e) {
            this.setViolations(e.getMessage());
            return false;
        }
        return true;
    }

    @JsonIgnore
    public boolean isValid(){
        return this.getViolations().length == 0;
    }

}
