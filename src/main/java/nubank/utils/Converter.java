/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nubank.model.AccountViolations;
import nubank.model.Transaction;

import java.io.IOException;

public class Converter {

    static public String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO: validate the json properly
        }
        return json;
}

    public static AccountViolations fromJsonToAccountViolations(String json) {
        AccountViolations accountViolations = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            accountViolations = mapper.readValue(json, AccountViolations.class);
        } catch (JsonProcessingException e) {
            System.out.println("DEU PAU NO DESERIALIZER!!");
            e.printStackTrace(); //TODO: validate the json properly
        } catch (IOException e) {
            e.printStackTrace(); //TODO: validate the json properly
        }
        return accountViolations;
    }

    public static Transaction fromJsonToTransaction(String json) {
        Transaction transaction = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            transaction = mapper.readValue(json, Transaction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO: validate the json properly
        } catch (IOException e) {
            e.printStackTrace(); //TODO: validate the json properly
        }
        return transaction;
    }

    public static boolean isAccountJson(String json) {
        return json.replace(" ", "").startsWith("{\"account\":");
    }

    public static String removeLineEnds(String s) {
        return s.replace("\r", "").replace("\n", "");
    }
}
