package nubank.validator

import nubank.TransactionProcessor
import nubank.model.Account
import nubank.model.Transaction
import spock.lang.Specification

import java.time.Instant

class TransactionValidatorTest extends Specification {
    Account account;
    TransactionProcessor processor;

    def setup() {
        account = new Account();
        processor = new TransactionProcessor();
    }

    def "processing a transaction"() {
        given:
        account.createAccount(true, 500);
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());

        when:
        processor.addTransaction(transaction, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 470
    }

    def "processing a transaction with no activeCard"() {
        given:
        account.createAccount(false, 500);
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());

        when:
        processor.addTransaction(transaction, account);

        then:
        account.getActiveCard() == false
        account.getAvailableLimit() == 500
        account.getViolations().length == 1
        account.getViolations()[0] == "card-not-active"
    }
}
