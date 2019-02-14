package csulb.cecs274;
import java.time.LocalDate;
import java.util.ArrayList;
public class BankCustomer {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private ArrayList<BankAccount> list;

    public BankCustomer(String first, String last, LocalDate birthday) {
        this.list =new ArrayList<>(5);
    }
    public int getNumberOfAccounts(){
        return list.size();
    }
}
