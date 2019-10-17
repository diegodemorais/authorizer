package nubank.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account")
@JsonPropertyOrder({"activeCard", "availableLimit", "violations"})
public class Account extends Base {
    private Boolean activeCard;
    private Integer availableLimit;

    public Account() {
        super();
        this.setAccount(null, 0);
    }

    public Account createAccount(boolean activeCard, int availableLimit) {
        if (this.validate())  {
            this.setAccount(activeCard, availableLimit);
        }
        return this;
    }

    public Boolean getActiveCard() {
        return activeCard;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }

    private void setAccount(Boolean activeCard, int availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
    }

}
