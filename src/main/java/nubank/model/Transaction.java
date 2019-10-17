package nubank.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.time.Instant;

@JsonRootName("transaction")
public class Transaction extends Base {
    private String merchant;
    private int amount;
    private Instant time;

    public Transaction(String merchant, int amount, String time) {
        this.merchant = merchant;
        this.amount = amount;
        this.time = Instant.parse(time);
    }

    public String getMerchant() {
        return merchant;
    }

    public int getAmount() {
        return amount;
    }

    public Instant getTime() {
        return time;
    }
}
