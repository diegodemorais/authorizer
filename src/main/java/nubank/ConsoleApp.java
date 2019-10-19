package nubank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nubank.model.Account;
import nubank.model.Transaction;
import nubank.utils.Converter;

import java.util.Scanner;

public class ConsoleApp
{
    public static void main( String[] args ) {
        Account account = new Account();
        TransactionProcessor processor = new TransactionProcessor();

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String json = input.nextLine();
            if (Converter.isAccountJson(json)) {
                Account accountFromJson  = Converter.fromJsonToAccount(json);
                account.createAccount(accountFromJson.getActiveCard(), accountFromJson.getAvailableLimit());
            } else {
                Transaction transactionFromJson = Converter.fromJsonToTransaction(json);
                Transaction transaction = new Transaction(transactionFromJson.getMerchant(), transactionFromJson.getAmount(), transactionFromJson.getTime().toString());
                processor.addTransaction(transaction, account);
            }
            System.out.println(Converter.toJson(account));
        }
    }
}
