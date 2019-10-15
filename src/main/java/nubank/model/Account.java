package nubank.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account")
public class Account {
    private boolean activeCard;
    private int availableLimit;

    public Account(boolean activeCard, int availableLimit){
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }
}
