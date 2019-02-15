package csulb.cecs274;
import java.time.LocalDate;
import java.math.*;

public class BankAccount {

    public enum BankAccountType {
        CHECKING, SAVINGS, MONEY_MARKET;
    }
     private BankCustomer owner;
     private BankAccountType type;
     public float balance;
     private float rate;
     private LocalDate startDate;

    public BankAccount(BankCustomer owner, BankAccountType type, float balance, float rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    public BankAccount(BankCustomer owner, float balance, float rate, LocalDate startDate ) {
        this.owner = owner;
        this.type  = BankAccountType.CHECKING;
        this.balance = balance;
        this.rate = rate;
        this.startDate = startDate;
    }
    public BankAccount(BankCustomer owner, BankAccountType type, float balance, float rate) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.rate = rate;
        this.startDate = LocalDate.now();
    }
    public BankAccount(BankCustomer owner, BankAccountType type, float balance, LocalDate startDate ) {
        this.owner = owner;
        this.type  = type;
        this.balance = balance;
        this.startDate = startDate;
        switch(type) {
            case CHECKING:
                this.rate = 0.0f;
                break;
            case SAVINGS:
                this.rate = 0.25f;
                break;
            case MONEY_MARKET:
                this.rate = 1.0f;
                break;
            default :
                this.rate = 0.0f;
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
//            System.out.println("Deposited: $" + credit);
//            System.out.println("Balance now: $" + this.balance);
        }
        else {
            System.out.println("Invalid amount entered!");
        }
    }
    public void withdraw(float debit){
        if(debit > 0 && this.balance >= debit) {
            this.balance -= debit;
//            System.out.println("withdrew: $" + debit);
//            System.out.println("Balance now: $" + this.balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    public void transfer(float transferAmount, BankAccount recipient ){
        if(transferAmount > 0 && this.balance >= transferAmount && this.owner == recipient.owner) {
            this.balance -= transferAmount;
            recipient.balance += transferAmount;
//            System.out.println("Transfer complete!");
//            System.out.println("Recipient balance now: " + recipient.getBalance());
        } else {
            System.out.println("Error please try again..");
        }
    }
    public void accrueInterest() {
        float principle = this.balance;
        float interest = principle * (this.rate/100);
        BigDecimal bigDecimal = new BigDecimal(Float.toString(interest + principle));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
        this.balance = bigDecimal.floatValue();
//        System.out.println("Interest earned: " + Math.round(interest * 100.0)/ 100.0);
//        System.out.println("New balance: " + bigDecimal);
    }
    public String toString(){
        if (this.type !=BankAccountType.CHECKING) {
            return this.type + " account with balance $" + String.format("%,.2f", this.balance) + " owned by " +
                    this.owner + " interest rate: " + String.format("%,.2f", this.rate) + "%";
        } else {
            return this.type + " account with balance $" + String.format("%,.2f", this.balance) + " owned by " + this.owner;
        }
    }
}
