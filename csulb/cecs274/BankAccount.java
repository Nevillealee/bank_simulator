package csulb.cecs274;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.math.BigDecimal;


public class BankAccount {
    public enum BankAccountType {
        CHECKING, SAVINGS, MONEY_MARKET;
    }
     private BankCustomer owner;
     private BankAccountType type;
     private float balance;
     private float rate;
     private LocalDate startDate;

    // default constructor
    public BankAccount(BankCustomer owner, BankAccountType type, float balance, float rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    // Type of account is optional, default value is checking
    public BankAccount(BankCustomer owner, float balance, float rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = BankAccountType.CHECKING;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    // Date the account is opened and if not provided its initial value is the current date
    public BankAccount(BankCustomer owner, BankAccountType type, float balance, float rate) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = LocalDate.now();
    }
    //Interest rate is optional, default is lower bound of range of appropriate account type
    public BankAccount(BankCustomer owner, BankAccountType type, float balance, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.startDate = startDate;
        switch(type) {
            case CHECKING:
                this.rate = 0;
                break;
            case SAVINGS:
                this.rate = 0.25f;
                break;
            case MONEY_MARKET:
                this.rate = 1.0f;
                break;
            default :
                this.rate = 0;
        }
    }

    public BankAccountType getType() {
        return this.type;
    }
    public BankCustomer getOwner() {
        return this.owner;
    }
    public double getBalance() {
        return this.balance;
    }
    public void deposit(float credit){
        if (credit > 0) {
            this.balance += credit;
            System.out.println("Deposited: $" + credit);
            System.out.println("Balance now: $" + this.balance);
        }
        else {
            System.out.println("Invalid amount entered!");
        }
    }
    public void withdraw(float debit){
        if(debit > 0 && this.balance >= debit) {
            this.balance -= debit;
            System.out.println("withdrew: $" + debit);
            System.out.println("Balance now: $" + this.balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    public void transfer(float transferAmt, BankAccount recipient ){
        if(transferAmt > 0 && this.balance >= transferAmt && this.owner == recipient.owner) {
            this.balance -= transferAmt;
            recipient.balance += transferAmt;
            System.out.println("Transfer complete!");
            System.out.println("Recipient balance now: " + recipient.getBalance());
        } else {
            System.out.println("Error please try again..");
        }
    }
    public void accrueInterest() {
        float principle = this.balance;
        float interest = principle * (this.rate/100);
        BigDecimal bd = new BigDecimal(Float.toString(interest + principle));
        bd = bd.setScale(2, RoundingMode.HALF_DOWN);
        this.balance = bd.floatValue();
        System.out.println("Interest earned: " + Math.round(interest * 100.0)/ 100.0);
        System.out.println("New balance: " + bd);
    }
    // change this.owner to this.owner.getName() after BankCustomer class code completed
    public String toString(){
        if (this.type !=BankAccountType.CHECKING) {
            return this.type + " with balance $" + String.format("%,.2f", this.balance) + " owned by " + this.owner + " interest rate: " + this.rate + "%";
        } else {
            return this.type + " with balance $" + String.format("%,.2f", this.balance) + " owned by " + this.owner;
        }
    }
}
