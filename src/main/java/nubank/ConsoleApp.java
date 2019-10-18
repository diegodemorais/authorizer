package nubank;

import nubank.model.Account;
import nubank.model.Transaction;

/**
 * Hello world!
 *
 */
public class ConsoleApp
{
    public static void main( String[] args ) {
        Account account = new Account();
        account.createAccount(false, 100);
        System.out.println(nubank.utils.Converter.toJson(account));
//        account.createAccount(true, 350);
//        System.out.println(nubank.utils.Converter.toJson(account));
//        account.createAccount(true, 200);
//        System.out.println(nubank.utils.Converter.toJson(account));

        TransactionProcessor processor = new TransactionProcessor();

        Transaction transaction = new Transaction(account, "teste", 30,"2019-02-13T10:00:00.000Z");
        processor.addTransaction(account, transaction);

        System.out.println(nubank.utils.Converter.toJson(account));

    }
}
