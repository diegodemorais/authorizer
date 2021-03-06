/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.validator;

import nubank.TransactionProcessor;
import nubank.exception.CardNotActiveException;
import nubank.exception.DoubledTransactionException;
import nubank.exception.HighFrequencySmallIntervalException;
import nubank.exception.InsufficientLimitException;
import nubank.model.Account;
import nubank.model.Transaction;
import nubank.ivalidator.ITransactionProcessorValidator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionProcessorValidator implements ITransactionProcessorValidator {

    InsufficientLimit {
        public void validate(Account account, Transaction transaction, TransactionProcessor processor) throws InsufficientLimitException {
            if (account.getAvailableLimit() - transaction.getAmount() < 0) {
                throw new InsufficientLimitException("insufficient-limit");
            }
        }
    },
    CardNotActive {
        public void validate(Account account, Transaction transaction, TransactionProcessor processor) throws CardNotActiveException {
            if (account.getActiveCard() == null || !account.getActiveCard()) {
                throw new CardNotActiveException("card-not-active");
            }
        }
    },
    HighFrequencySmallInterval {
        public void validate(Account account, Transaction transaction, TransactionProcessor processor) throws HighFrequencySmallIntervalException {
            if (processor.getTransactions(transaction.getTime(), 2).size() >=3) {
                throw new HighFrequencySmallIntervalException("high-frequency-small-interval");
            }
        }
    },
    DoubledTransaction {
        public void validate(Account account, Transaction transaction, TransactionProcessor processor) throws DoubledTransactionException {
            LinkedHashMap<Transaction, Account> transactions = processor.getTransactions(transaction.getTime(), 2);
            Iterator it = transactions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Transaction currentTransaction = (Transaction)pair.getKey();
                if (transaction.getAmount().equals(currentTransaction.getAmount())
                        && transaction.getMerchant().equals(currentTransaction.getMerchant())) {
                    throw new DoubledTransactionException("doubled-transaction");
                }
//                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    };
}
