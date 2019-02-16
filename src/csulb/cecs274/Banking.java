package csulb.cecs274;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
public class Banking {
    /** This main method creates the necessary instances required for the Main class.
     *
     * @author Linette B. Murillo
     */
    public static void main(String[] args) {
        //Creating random balances for accounts
        Random randomBalance = new Random();
        float linetteMurilloCheckingBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float janeDoeCheckingBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float johnDoeCheckingBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float linetteMurilloSavingsBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float janeDoeSavingsBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float linetteMurilloMoneyMarketBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        float johnDoeMoneyMarketBalance = 100.0f + randomBalance.nextFloat() * (2500.0f -100.0f);
        //Creating random interest rates for accounts
        Random interestRate = new Random();
        Float linetteMurilloSavingsRate = interestRate.nextFloat()*(1.0f-0.25f)+0.25f;
        Float janeDoeSavingsRate = interestRate.nextFloat()*(1.0f-0.25f)+0.25f;
        Float linetteMurilloMoneyMarketRate = interestRate.nextFloat()*(2.0f-1.25f)+1.0f;
        Float johnDoeMoneyMarketRate = interestRate.nextFloat()*(2.0f-1.25f)+1.0f;
        //Creation of BankCustomer Objects
        ArrayList<BankCustomer> customerObjects = new ArrayList<>(3);
        BankCustomer linetteMurillo = new BankCustomer("Linette", "Murillo", LocalDate.of(1996, 12, 12));
        BankCustomer janeDoe = new BankCustomer("Jane", "Doe", LocalDate.of(2003, 03, 18));
        BankCustomer johnDoe = new BankCustomer("John", "Doe", LocalDate.of(1953, 12, 26));
        customerObjects.add(linetteMurillo);
        customerObjects.add(janeDoe);
        customerObjects.add(johnDoe);
        //Creation of BankAccount Objects
        ArrayList<BankAccount> bankAccountObjects = new ArrayList<>(6);
        BankAccount linetteMurilloChecking = new BankAccount(linetteMurillo,linetteMurilloCheckingBalance ,0, linetteMurillo.customerBirthday);
        BankAccount janeDoeChecking = new BankAccount(janeDoe, janeDoeCheckingBalance,0.0f, janeDoe.customerBirthday);
        BankAccount johnDoeChecking = new BankAccount(johnDoe, johnDoeCheckingBalance,0.0f, johnDoe.customerBirthday);
        BankAccount linetteMurilloSavings = new BankAccount(linetteMurillo, BankAccount.BankAccountType.SAVINGS, linetteMurilloSavingsBalance, linetteMurilloSavingsRate, java.time.LocalDate.of(2001,02,18));
        BankAccount janeDoeSavings = new BankAccount(janeDoe, BankAccount.BankAccountType.SAVINGS, janeDoeSavingsBalance,janeDoeSavingsRate , java.time.LocalDate.of(2001,02,16));
        BankAccount linetteMurilloMoneyMarket = new BankAccount(linetteMurillo, BankAccount.BankAccountType.MONEY_MARKET, linetteMurilloMoneyMarketBalance, linetteMurilloMoneyMarketRate, java.time.LocalDate.of(2005,03,23));
        BankAccount johnDoeMoneyMarket = new BankAccount(johnDoe, BankAccount.BankAccountType.MONEY_MARKET, johnDoeMoneyMarketBalance, johnDoeMoneyMarketRate, java.time.LocalDate.of(2013, 11, 17));
        //Adding BankAccount objects to bankAccountObjects ArrayList
        bankAccountObjects.add(linetteMurilloChecking);
        bankAccountObjects.add(janeDoeChecking);
        bankAccountObjects.add(johnDoeChecking);
        bankAccountObjects.add(linetteMurilloSavings);
        bankAccountObjects.add(janeDoeSavings);
        bankAccountObjects.add(linetteMurilloMoneyMarket);
        bankAccountObjects.add(johnDoeMoneyMarket);
        //Adding BankAccount objects to individual BankCustomer lists
        linetteMurillo.list.add(linetteMurilloChecking);
        linetteMurillo.list.add(linetteMurilloChecking);
        linetteMurillo.list.add(linetteMurilloChecking);
        linetteMurillo.list.add(linetteMurilloSavings);
        linetteMurillo.list.add(linetteMurilloMoneyMarket);
        janeDoe.list.add(janeDoeChecking);
        janeDoe.list.add(janeDoeSavings);
        johnDoe.list.add(johnDoeChecking);
        johnDoe.list.add(johnDoeMoneyMarket);

        try {
            linetteMurillo.addAccount(johnDoeMoneyMarket);
        }catch(LimitOnAccountsExceeded e)
        {
            e.getMessage();
            System.out.println("limited exceeded:" + linetteMurillo.list.size());
        }catch(CustomerOwnsAccountAlready e)
        {
            e.getMessage();
            System.out.println("account owned already!");
        }


        while (customerObjects.size() == 3) {
            for (int i = 0; i < customerObjects.size(); i++) {
                customerObjects.get(i).getBirthdate();
                customerObjects.get(i).getNumberOfAccounts();
                customerObjects.get(i).getTotalBalance(); }
            break;
            }
        System.out.println();
        for (int i = 0; i < bankAccountObjects.size(); i++) {
            System.out.println(bankAccountObjects.get(i).toString()); }
        for (BankAccount bankAccount : bankAccountObjects ) {
            bankAccount.accrueInterest(); }
        }
}