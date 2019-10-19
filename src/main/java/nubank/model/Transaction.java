package nubank.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.Instant;

@JsonRootName("transaction")
public class Transaction {
    private String merchant;
    private Integer amount;
    private Instant time;

    public Transaction(){

    }

    public Transaction(String merchant, int amount, String time) {
        this.setTransaction(merchant, amount, time);
    }

    private void setTransaction(String merchant, Integer amount, String time) {
        this.merchant = merchant;
        this.amount = amount;
        this.setTime(time);
    }

    public String getMerchant() {
        return merchant;
    }

    public Integer getAmount() {
        return amount;
    }

    public Instant getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time == null ? null : Instant.parse(time);
    }


}
