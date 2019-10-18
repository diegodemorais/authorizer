package nubank;

import nubank.model.Account;
import nubank.model.Transaction;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class TransactionProcessor {

    private LinkedHashMap<Account,Transaction> transactions;

    public TransactionProcessor() {
        this.transactions = new LinkedHashMap<>();
    }

    public LinkedHashMap<Account,Transaction>  getTransactions() {
        return transactions;
    }

    public void addTransaction(Account account, Transaction transaction) {
        if (transaction.isValid()) {
            this.applyTransaction(account, transaction);
            this.transactions.put(account,transaction);
        }
    }

    public void applyTransaction(Account account, Transaction transaction){
        account.applyDebit(transaction.getAmount());
    }
}
