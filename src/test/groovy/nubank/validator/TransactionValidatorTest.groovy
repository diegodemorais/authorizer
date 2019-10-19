package nubank.validator

import nubank.TransactionProcessor
import nubank.model.Account
import nubank.model.Transaction
import spock.lang.Specification

import java.time.Instant
import java.time.temporal.ChronoUnit

class TransactionValidatorTest extends Specification {
    Account account;
    TransactionProcessor processor;

    def setup() {
        account = new Account();
        processor = new TransactionProcessor();
    }

    def "processing some successful transactions"() {
        given:
        account.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, account);
        Transaction transaction2 = new Transaction("teste2", 20, Instant.now().toString());
        processor.addTransaction(transaction2, account);
        Transaction transaction3 = new Transaction("teste3", 40, Instant.now().toString());
        processor.addTransaction(transaction3, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 410
        account.getViolations().length == 0
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

    def "processing same transaction twice in less than 2 min interval"() {
        given:
        account.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, account);
        Transaction transaction2 = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction2, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 470
        account.getViolations()[0] == "doubled-transaction"
    }

    def "processing same transaction twice in more than 2 min interval"() {
        given:
        account.createAccount(true, 500);

        when:
        String first = Instant.now().minus(5, ChronoUnit.MINUTES).toString();
        System.out.println("First: " + first)
        Transaction transaction = new Transaction("teste", 30, first);
        processor.addTransaction(transaction, account);

        String second = Instant.now().toString();
        System.out.println("Second: " + second)
        Transaction transaction2 = new Transaction("teste", 30, second);
        processor.addTransaction(transaction2, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 440
        account.getViolations().length == 0
    }

    def "processing more than 3 transactions in less than 2 min interval"() {
        given:
        account.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, account);
        Transaction transaction2 = new Transaction("teste2", 30, Instant.now().toString());
        processor.addTransaction(transaction2, account);
        Transaction transaction3 = new Transaction("teste3", 30, Instant.now().toString());
        processor.addTransaction(transaction3, account);
        Transaction transaction4 = new Transaction("teste4", 30, Instant.now().toString());
        processor.addTransaction(transaction4, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 410
        account.getViolations()[0] == "high-frequency-small-interval"
    }

    def "processing more than 3 transactions in more than 2 min interval"() {
        given:
        account.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().minus(5, ChronoUnit.MINUTES).toString());
        processor.addTransaction(transaction, account);
        Transaction transaction2 = new Transaction("teste2", 30, Instant.now().toString());
        processor.addTransaction(transaction2, account);
        Transaction transaction3 = new Transaction("teste3", 30, Instant.now().toString());
        processor.addTransaction(transaction3, account);
        Transaction transaction4 = new Transaction("teste4", 30, Instant.now().toString());
        processor.addTransaction(transaction4, account);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 380
        account.getViolations().length == 0
    }
}
