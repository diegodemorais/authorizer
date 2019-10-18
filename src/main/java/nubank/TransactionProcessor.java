package nubank;

import nubank.model.Account;
import nubank.model.Transaction;

import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class TransactionProcessor {

    private LinkedHashMap<Transaction, Account> transactions;

    public TransactionProcessor() {
        this.transactions = new LinkedHashMap<>();
    }

    public LinkedHashMap<Transaction, Account> getTransactions() {
        return transactions;
    }

    public LinkedHashMap<Transaction, Account> getTransactions(Integer fromMinutesAgo) {
        Instant minutuesAgo = Instant.now().minusSeconds(fromMinutesAgo*60);
        LinkedHashMap<Transaction, Account> transactionsFromMinutesAgo = new LinkedHashMap<>();
        Iterator iterator = this.getTransactions().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            Transaction currentTransaction = (Transaction)pair.getKey();
            if (currentTransaction.getTime().compareTo(minutuesAgo) >= 0 ){
                transactionsFromMinutesAgo.put(currentTransaction, (Account)pair.getValue());
            }
        }

        return transactions;
    }

    public void addTransaction(Transaction transaction, Account account) {
        if (transaction.isValid()) {
            this.applyTransaction(transaction, account);
            this.transactions.put(transaction, account);
        }
    }

    public void applyTransaction(Transaction transaction, Account account){
        account.applyDebit(transaction.getAmount());
    }
}
