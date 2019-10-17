package nubank;

import nubank.model.Account;

/**
 * Hello world!
 *
 */
public class ConsoleApp
{
    public static void main( String[] args ) {
        Account account = new Account();
        account.createAccount(true, 100);
        System.out.println(nubank.utils.Converter.toJson(account));
        account.createAccount(true, 350);
        System.out.println(nubank.utils.Converter.toJson(account));
        account.createAccount(true, 200);
        System.out.println(nubank.utils.Converter.toJson(account));
    }
}
