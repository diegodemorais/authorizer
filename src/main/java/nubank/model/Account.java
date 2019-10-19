package nubank.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account")
public class Account {
    private Boolean activeCard;
    private Integer availableLimit;

    public Account(){

    }

    public Account(Boolean activeCard, int availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
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
        }
    }

}
