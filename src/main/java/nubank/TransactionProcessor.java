package nubank;

import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.AccountViolations;
import nubank.model.Transaction;
import nubank.ivalidator.ITransactionProcessorValidator;
import nubank.validator.TransactionProcessorValidator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionProcessor {

    private LinkedHashMap<Transaction, Account> transactions;

    public TransactionProcessor() {
        this.transactions = new LinkedHashMap<>();
    }

    public LinkedHashMap<Transaction, Account> getAllTransactions() {
        return this.transactions;
    }

    public LinkedHashMap<Transaction, Account> getTransactions(Instant base, Integer intervalInMinutes) {
        LinkedHashMap<Transaction, Account> transactionsFiltered = new LinkedHashMap<>();
        Iterator iterator = this.getAllTransactions().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Transaction currentTransaction = (Transaction) pair.getKey();
            Instant start = base.minus(intervalInMinutes,ChronoUnit.MINUTES);
            Instant end = base.plus(intervalInMinutes,ChronoUnit.MINUTES);
            if (currentTransaction.getTime().compareTo(start) >= 0
                && currentTransaction.getTime().compareTo(end) <= 0) {
                transactionsFiltered.put(currentTransaction, (Account) pair.getValue());
            }
        }
        return transactionsFiltered;
    }

    public void addTransaction(Transaction transaction, AccountViolations accountViolations) {
        accountViolations.resetViolations();
        this.validate(accountViolations, transaction, TransactionProcessorValidator.CardNotActive);
        this.validate(accountViolations, transaction, TransactionProcessorValidator.InsufficientLimit);
        this.validate(accountViolations, transaction, TransactionProcessorValidator.HighFrequencySmallInterval);
        this.validate(accountViolations, transaction, TransactionProcessorValidator.DoubledTransaction);
        this.applyTransaction(transaction, accountViolations);
    }

    void validate(AccountViolations accountViolations, Transaction transaction, ITransactionProcessorValidator validator) {
        if (accountViolations.isValid()) { //Prioritizing validations: the previous is the priority
            try {
                validator.validate(accountViolations.getAccount(), transaction, this);
            } catch (BusinessException e) {
                accountViolations.setViolations(e.getMessage());
            }
        }
    }

    public void applyTransaction(Transaction transaction, AccountViolations accountViolations) {
        if (accountViolations.isValid()) {
            accountViolations.getAccount().applyDebit(transaction.getAmount());
            accountViolations.resetViolations();
            this.transactions.put(transaction, accountViolations.getAccount());
        }
    }
}
