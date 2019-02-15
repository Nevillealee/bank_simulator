package csulb.cecs274;

import java.time.LocalDate;
import java.util.Scanner;

public class Banking {
    public static void main(String[] args){
    // you have access to BankAccount or BankCustomer classes now
        BankCustomer tuggie = new BankCustomer();
        BankAccount tugChecking = new BankAccount(tuggie, 0, 0.225f, LocalDate.EPOCH);
        BankAccount tugSavings = new BankAccount(tuggie, BankAccount.BankAccountType.SAVINGS,0.00f, 0.05f
        );

        System.out.println(tugChecking.getBalance());
        tugChecking.deposit(10000.00f);
        tugChecking.accrueInterest();
        System.out.println(tugSavings.toString());
    }
}