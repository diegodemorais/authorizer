package nubank.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import nubank.model.Transaction;

import java.io.IOException;

public class TransactionDeserializer extends StdDeserializer<Transaction> {

    public TransactionDeserializer() {
        this(null);
    }

    public TransactionDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public Transaction deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        final String merchant = node.get("merchant").asText();
        final Integer amount =  node.get("amount").asInt();
        final String time = node.get("time").asText();
        return new Transaction(merchant, amount, time);
    }
}
