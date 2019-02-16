package csulb.cecs274;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.lang.Exception;
/**
 * Each BankCustomer object models customer information for a single customer
 * at the bank.
 * @author Linette B. Murillo
 */
class LimitOnAccountsExceeded extends Exception {
    public LimitOnAccountsExceeded(String message) { super(message); }
}
class CustomerOwnsAccountAlready extends Exception {
    public CustomerOwnsAccountAlready(String message) { super(message); }
}
public class BankCustomer {
    private String firstName;
    private String lastName;
    public LocalDate customerBirthday;
    private int age;
    public ArrayList<BankAccount> list;
    public Float totalBalance = 0.0f;
    public Float currentAccountBalance;

    /**Creates an instance of a BankCustomer and a respective list to track
     * all accounts for that customer, not exceeding 5 accounts.
     *
     * @param first
     * @param last
     * @param birthday
     */
    public BankCustomer(String first, String last, LocalDate birthday) {
        this.list = new ArrayList<>(5);
        this.firstName = first;
        this.lastName = last;
        this.customerBirthday = birthday;
    }
    String getFirstName() {
        return firstName;
    }
    String getLastName() {
        return lastName;
    }
    String getName() {
        return firstName + " " + lastName;
    }
    LocalDate getBirthdate() {
        return customerBirthday;
    }
    /** Uses java.time.Period to calculate the difference between today
     * and the customer's birthday.
     *
     * @return BankCustomer.age
     */
    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period differenceInTime = Period.between(customerBirthday, currentDate);
        int days = differenceInTime.getDays();
        int daysOverYears = days / 365;
        int months = differenceInTime.getMonths();
        int monthsOverYears = months / 12;
        int years = differenceInTime.getYears();
        int age = years + daysOverYears + monthsOverYears;
        return age;
    }
    /** Determines the current number of BankAccount objects for a given BankCustomer.
     *
     * @return the size of the list to determine how many BankAccount objects exist.
     */
    public int getNumberOfAccounts() {
        return list.size();
    }
    /** Retrieves the total balance of all accounts for a single BankCustomer
     * by iterating through the BankCustomer list and summing each account.
     *
     * @return the total balance for the BankCustomer.
     */
    public Float getTotalBalance() {
        for (int i = 0; i <= list.size()-1; i++) {
            currentAccountBalance = list.get(i).balance;
            totalBalance += currentAccountBalance;
        }
        return totalBalance;
    }
    //
//    addAccount(): mutator that takes a BankAccount object as a parameter and adds it to the list of
//    accounts the customer owns as long this action does not violate the limit on number of accounts that
//    can be owned. This method can throw two exceptions: LimitOnAccountsExceeded and CustomerOwnsAccountAlready.
//    The LimitOnAccountsExceeded is thrown when the customer already has reached the limit on accounts they own, so
//    the request to add another account failed. The CustomerOwnsAccountAlready is thrown when the account sent via
//    the parameter is already in the list of accounts owned by the customer.
    public void addAccount(BankAccount myNewAccount) throws LimitOnAccountsExceeded, CustomerOwnsAccountAlready {
        if (this.list.size() > 4){
            throw new LimitOnAccountsExceeded("Limit on account exceeded!");
        }
        if (this.list.contains(myNewAccount)) {
            throw new CustomerOwnsAccountAlready("Customer owns account already!");
        }
        this.list.add(myNewAccount);
        //myAccounts.add(myNewAccount);
    }
    //overload different addAccount methods
    /** Appropriately formats the objects to print the return values.
     *
     * @return BankCustomer
     */
    public String toString() {
        return this.getName() + " owns " + this.getNumberOfAccounts() + " accounts with a total of $ "
                + String.format("%,.2f", this.totalBalance) + ".";
    }
}