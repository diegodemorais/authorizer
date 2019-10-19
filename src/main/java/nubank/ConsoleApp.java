package nubank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nubank.model.Account;
import nubank.model.Transaction;
import nubank.utils.Converter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp
{
    public static void main( String[] args ) {
        Authorizer program = new Authorizer();
        program.executeFromStdinToStdout();
    }
}
