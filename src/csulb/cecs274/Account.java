package csulb.cecs274;
import java.time.LocalDate;

public enum BankAccountType {
    CHECKING, SAVINGS, MONEY_MARKET;
}

public class BankAccount {
     private BankCustomer owner;
     private BankAccountType type;
     private double balance;
     private float rate;
     private LocalDate startDate;

    // default constructor
    public Account(BankCustomer owner, BankAccountType type, float balance, double rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    // Type of account is optional, default value is checking
    public Account(BankCustomer owner, double balance, float rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = BankAccountType.CHECKING;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    // Date the account is opened and if not provided its initial value is the current date
    public Account(BankCustomer owner, BankAccountType type, double balance, float rate) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = LocalDate.now();
    }
    //Interest rate is optional, default is lower bound of range of appropriate account type
    public Account(BankCustomer owner, BankAccountType type, double balance, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.startDate = startDate;
        switch(type) {
            case CHECKING:
                this.rate = 0;
                break;
            case SAVINGS:
                this.rate = 0.25;
                break;
            case MONEY_MARKET:
                this.rate = 1.0;
                break;
            default :
                this.rate = 0;
        }
    }
}
