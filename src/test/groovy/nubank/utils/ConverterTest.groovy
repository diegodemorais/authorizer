package nubank.utils

import nubank.model.Account
import nubank.model.AccountViolations
import nubank.model.Transaction
import spock.lang.Specification

import java.time.Instant

class ConverterTest extends Specification {
    AccountViolations accountViolations;

    def "convert a json to Account"() {
        given:
        String json = "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }"

        when:
        accountViolations = Converter.fromJsonToAccountViolations(json);

        then:
        accountViolations.getAccount().getActiveCard() == true
        accountViolations.getAccount().getAvailableLimit() == 100
    }

    def "convert a json to Transaction"() {
        given:
        String json = "{ \"transaction\": { \"merchant\": \"Habbib's\", \"amount\": 90, \"time\": \"2019-02-13T11:00:00.000Z\" } }"

        when:
        Transaction transaction = Converter.fromJsonToTransaction(json);

        then:
        transaction.getMerchant() == "Habbib's"
        transaction.getAmount() == 90
        transaction.getTime().toString() == Instant.parse("2019-02-13T11:00:00.000Z").toString()
    }

}
