package nubank.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import nubank.exception.BusinessException;
import nubank.validator.IAccountValidator;
import nubank.validator.ITransactionValidator;
import nubank.validator.TransactionValidator;

import java.time.Instant;

@JsonRootName("transaction")
public class Transaction {
    private String merchant;
    private Integer amount;
    private Instant time;

    public Transaction(Account account, String merchant, int amount, String time) {
        this.setTransaction(merchant, amount, time);
        this.validate(account, this, TransactionValidator.CardNotActive);
        this.validate(account, this, TransactionValidator.InsufficientLimit);
        this.tryResetTransaction(account);
    }

    void validate(Account account, Transaction transaction, ITransactionValidator validator) {
        try {
            validator.validate(account, transaction);
        } catch (BusinessException e) {
            account.setViolations(e.getMessage());
        }
    }

    private void setTransaction(String merchant, Integer amount, String time) {
        this.merchant = merchant;
        this.amount = amount;
        this.time = time == null ? null : Instant.parse(time);
    }

    private void tryResetTransaction(Account account) {
        if (account.getViolations().length > 0) {
            this.setTransaction(null, null, null);
        }
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

    public Boolean isValid(){
        return this.getAmount() == null || this.getAmount() > 0;
    }
}
