package nubank;

import nubank.exception.BusinessException;
import nubank.model.Account;
import nubank.model.Transaction;
import nubank.validator.ITransactionProcessorValidator;
import nubank.validator.TransactionProcessorValidator;

import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionProcessor {

    private LinkedHashMap<Transaction, Account> transactions;

    public TransactionProcessor() {
        this.transactions = new LinkedHashMap<>();
    }

    public LinkedHashMap<Transaction, Account> getAllTransactions() {
        return transactions;
    }

    public LinkedHashMap<Transaction, Account> getAllTransactions(Integer fromMinutesAgo) {
        Instant minutesAgo = Instant.now().minusSeconds(fromMinutesAgo * 60);
        LinkedHashMap<Transaction, Account> transactionsFromMinutesAgo = new LinkedHashMap<>();
        Iterator iterator = this.getAllTransactions().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Transaction currentTransaction = (Transaction) pair.getKey();
            if (currentTransaction.getTime().compareTo(minutesAgo) >= 0) {
                transactionsFromMinutesAgo.put(currentTransaction, (Account) pair.getValue());
            }
        }

        return transactions;
    }

    public void addTransaction(Transaction transaction, Account account) {
        account.resetViolations();
        this.validate(account, transaction, TransactionProcessorValidator.CardNotActive);
        this.validate(account, transaction, TransactionProcessorValidator.InsufficientLimit);
        this.validate(account, transaction, TransactionProcessorValidator.HighFrequencySmallInterval);
        this.validate(account, transaction, TransactionProcessorValidator.DoubledTransaction);
        this.applyTransaction(transaction, account);
    }

    void validate(Account account, Transaction transaction, ITransactionProcessorValidator validator) {
        if (account.isValid()) { //Prioritizing validations: the previous is the priority
            try {
                validator.validate(account, transaction, this);
            } catch (BusinessException e) {
                account.setViolations(e.getMessage());
            }
        }
    }

    public void applyTransaction(Transaction transaction, Account account) {
        if (account.isValid()) {
            account.applyDebit(transaction.getAmount());
            this.transactions.put(transaction, account);
        }
    }
}
