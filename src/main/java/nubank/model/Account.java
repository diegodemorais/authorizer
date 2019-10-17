package nubank.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import nubank.validator.AccountValidator;

@JsonRootName("account")
@JsonPropertyOrder({ "activeCard", "availableLimit", "violations" })
public class Account extends Base {
    private Boolean activeCard;
    private Integer availableLimit;

    public Account(){
        super(new AccountValidator());
        this.setAccount(null,0);
    }

    public Account createAccount(boolean activeCard, int availableLimit)  {
        try {
            this.getValidator().validate(this);
            this.setAccount(activeCard, availableLimit);
            this.initilizeViolations();
        } catch (Exception e) {
            this.setViolations(e.getMessage());
        }
        return this;
    }

    public Boolean getActiveCard() {
        return activeCard;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }

    private void setAccount(Boolean activeCard, int availableLimit)  {
       this.activeCard = activeCard;
       this.availableLimit = availableLimit;
    }

}
