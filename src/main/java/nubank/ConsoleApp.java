package nubank;

import nubank.model.Account;
import nubank.model.Transaction;
import nubank.utils.Converter;

import java.time.Instant;

/**
 * Hello world!
 *
 */
public class ConsoleApp
{
    public static void main( String[] args ) {
//        Account account = new Account();
//        account.createAccount(true, 500);
//        System.out.println(nubank.utils.Converter.toJson(account));
//        account.createAccount(true, 350);
//        System.out.println(nubank.utils.Converter.toJson(account));
//        account.createAccount(true, 200);
//        System.out.println(nubank.utils.Converter.toJson(account));

//        TransactionProcessor processor = new TransactionProcessor();
//
//        Transaction transaction = new Transaction("teste", 30, Instant.now().toString());
//        processor.addTransaction(transaction, account);
//        System.out.println(nubank.utils.Converter.toJson(account));
//
//        Transaction transaction2 = new Transaction("teste2", 30,Instant.now().toString());
//        processor.addTransaction(transaction2, account);
//        System.out.println(nubank.utils.Converter.toJson(account));
//
//        Transaction transaction3 = new Transaction("teste3", 30,Instant.now().toString());
//        processor.addTransaction(transaction3, account);
//        System.out.println(nubank.utils.Converter.toJson(account));
//
//        Transaction transaction4 = new Transaction("teste4", 30,Instant.now().toString());
//        processor.addTransaction(transaction4, account);
//        System.out.println(nubank.utils.Converter.toJson(account));
        String json = "{ \"transaction\": { \"merchant\": \"Habbib's\", \"amount\": 90, \"time\": \"2019-02-13T11:00:00.000Z\" } }";
        Transaction transaction = Converter.fromJsonToTransaction(json);
    }
}
