package nubank.groovy

import nubank.Authorizer
import nubank.utils.Converter
import spock.lang.Specification

class AuthorizerTest extends Specification {
    Authorizer program = new Authorizer();
    ByteArrayOutputStream outMock = new ByteArrayOutputStream()

    def setup() {
        outMock = new ByteArrayOutputStream()
        System.setOut(new PrintStream(outMock));
    }

    def "integrated test successful"() {
        when:
        String fileIn = "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }\n" +
                "{ \"transaction\": { \"merchant\": \"Burger King\", \"amount\": 20, \"time\": \"2019-02-13T10:00:00.000Z\" } }\n" +
                "{ \"transaction\": { \"merchant\": \"Habbib's\", \"amount\": 90, \"time\": \"2019-02-13T11:00:00.000Z\" } } "
        ByteArrayInputStream inMock = new ByteArrayInputStream(fileIn.getBytes())
        System.setIn(inMock)
        program.executeFromStdinToStdout()

        then:
        String expected = "{\"account\":{\"activeCard\":true,\"availableLimit\":100},\"violations\":[]}\n" +
                "{\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[]}\n" +
                "{\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[\"insufficient-limit\"]}"
        Converter.removeLineEnds(outMock.toString()) == Converter.removeLineEnds(expected) //to ignore differences from System Operations Line Ends
    }


    def "integrated test doubled transacion"() {
        when:
        String fileIn = "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }\n" +
                "{ \"transaction\": { \"merchant\": \"Burger King\", \"amount\": 20, \"time\": \"2019-02-13T10:00:00.000Z\" } }\n" +
                "{ \"transaction\": { \"merchant\": \"Burger King\", \"amount\": 20, \"time\": \"2019-02-13T10:00:00.000Z\" } }"

        ByteArrayInputStream inMock = new ByteArrayInputStream(fileIn.getBytes())
        System.setIn(inMock)
        program.executeFromStdinToStdout()

        then:
        String expected = "{\"account\":{\"activeCard\":true,\"availableLimit\":100},\"violations\":[]}\n{" +
                "\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[]}\n" +
                "{\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[\"doubled-transaction\"]}"
        Converter.removeLineEnds(outMock.toString()) == Converter.removeLineEnds(expected) //to ignore differences from System Operations Line Ends
    }
}
