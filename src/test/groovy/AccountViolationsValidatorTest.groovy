package nubank.groovy

import nubank.model.AccountViolations
import spock.lang.Specification

class AccountViolationsValidatorTest extends Specification {
    AccountViolations accountViolations;

    def setup() {
        accountViolations = new AccountViolations();
    }

    def "create account"() {
        when:
        accountViolations.createAccount(true, 500);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 500
    }

    def "create account twice should be denied"() {
        given:
        accountViolations.createAccount(true, 500);

        when:
        accountViolations.createAccount(true, 350);

        then:
        accountViolations.getViolations().length == 1
        accountViolations.getViolations()[0] == "account-already-initialized"
    }
}
