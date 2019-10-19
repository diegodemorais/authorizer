/**
 * @project Nubank code challenge: Transaction Authorizer
 * @author Diego de Morais on oct/19
 */
package nubank;

import nubank.model.AccountViolations;
import nubank.model.Transaction;
import nubank.utils.Converter;
import java.util.Scanner;

public class Authorizer {

    private AccountViolations accountViolations;
    private TransactionProcessor processor;

    public Authorizer() {
        accountViolations = new AccountViolations();
        processor = new TransactionProcessor();
    }

    public String processJson(String json) {
        if (Converter.isAccountJson(json)) {
            AccountViolations accountViolationsFromJson = Converter.fromJsonToAccountViolations(json);
            accountViolations.createAccount(accountViolationsFromJson.getAccount().getActiveCard(), accountViolationsFromJson.getAccount().getAvailableLimit());
        } else {
            Transaction transactionFromJson = Converter.fromJsonToTransaction(json);
            Transaction transaction = new Transaction(transactionFromJson.getMerchant(), transactionFromJson.getAmount(), transactionFromJson.getTime().toString());
            processor.addTransaction(transaction, accountViolations);
        }
        return Converter.toJson(accountViolations);
    }

    public void executeFromStdinToStdout(){
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            System.out.println(processJson(input.nextLine()));
        }
    }
}

