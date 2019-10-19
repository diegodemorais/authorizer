/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.groovy

import nubank.TransactionProcessor
import nubank.model.Account
import nubank.model.AccountViolations
import nubank.model.Transaction
import spock.lang.Specification

import java.time.Instant
import java.time.temporal.ChronoUnit

class TransactionValidatorTest extends Specification {
    AccountViolations accountViolations;
    TransactionProcessor processor;

    def setup() {
        accountViolations = new AccountViolations();
        processor = new TransactionProcessor();
    }

    def "processing some successful transactions"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, accountViolations);
        Transaction transaction2 = new Transaction("teste2", 20, Instant.now().toString());
        processor.addTransaction(transaction2, accountViolations);
        Transaction transaction3 = new Transaction("teste3", 40, Instant.now().toString());
        processor.addTransaction(transaction3, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 410
        accountViolations.getViolations().length == 0
    }

    def "processing a transaction with no activeCard"() {
        given:
        accountViolations.createAccount(false, 500);
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());

        when:
        processor.addTransaction(transaction, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == false
        accountViolations.getAccount().getAvailableLimit() == 500
        accountViolations.getViolations().length == 1
        accountViolations.getViolations()[0] == "card-not-active"
    }

    def "processing same transaction twice in less than 2 min interval"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, accountViolations);
        Transaction transaction2 = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction2, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 470
        accountViolations.getViolations()[0] == "doubled-transaction"
    }

    def "processing same transaction twice in more than 2 min interval"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        String first = Instant.now().minus(5, ChronoUnit.MINUTES).toString();
        System.out.println("First: " + first)
        Transaction transaction = new Transaction("teste", 30, first);
        processor.addTransaction(transaction, accountViolations);

        String second = Instant.now().toString();
        System.out.println("Second: " + second)
        Transaction transaction2 = new Transaction("teste", 30, second);
        processor.addTransaction(transaction2, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 440
        accountViolations.getViolations().length == 0
    }

    def "processing more than 3 transactions in less than 2 min interval"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
        processor.addTransaction(transaction, accountViolations);
        Transaction transaction2 = new Transaction("teste2", 30, Instant.now().toString());
        processor.addTransaction(transaction2, accountViolations);
        Transaction transaction3 = new Transaction("teste3", 30, Instant.now().toString());
        processor.addTransaction(transaction3, accountViolations);
        Transaction transaction4 = new Transaction("teste4", 30, Instant.now().toString());
        processor.addTransaction(transaction4, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 410
        accountViolations.getViolations()[0] == "high-frequency-small-interval"
    }

    def "processing more than 3 transactions in more than 2 min interval"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        Transaction transaction = new Transaction("teste", 30, Instant.now().minus(5, ChronoUnit.MINUTES).toString());
        processor.addTransaction(transaction, accountViolations);
        Transaction transaction2 = new Transaction("teste2", 30, Instant.now().toString());
        processor.addTransaction(transaction2, accountViolations);
        Transaction transaction3 = new Transaction("teste3", 30, Instant.now().toString());
        processor.addTransaction(transaction3, accountViolations);
        Transaction transaction4 = new Transaction("teste4", 30, Instant.now().toString());
        processor.addTransaction(transaction4, accountViolations);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 380
        accountViolations.getViolations().length == 0
    }
}
