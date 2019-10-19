package nubank.validator

import nubank.model.Account
import spock.lang.Specification

class AccountValidatorTest extends Specification {
    Account account;

    def setup() {
        account = new Account();
    }

    def "create account"() {
        when:
        account.createAccount(true, 500);

        then:
        account.getActiveCard() == true
        account.getAvailableLimit() == 500
    }

    def "create account twice should be denied"() {
        given:
        account.createAccount(true, 500);

        when:
        account.createAccount(true, 350);

        then:
        account.getViolations().length == 1
        account.getViolations()[0] == "account-already-initialized"
    }
}
