package nubank.utils;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nubank.model.Account;
import nubank.model.Transaction;

public class Converter {

    static public String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Account fromJsonToAccount(String json) {
        Account account = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        account = mapper.readValue(json, Account.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO: validate the json properly
        }
        return account;
    }

    public static Transaction fromJsonToTransaction(String json) {
        Transaction transaction = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            transaction = mapper.readValue(json, Transaction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO: validate the json properly
        }
        return transaction;
    }
}
