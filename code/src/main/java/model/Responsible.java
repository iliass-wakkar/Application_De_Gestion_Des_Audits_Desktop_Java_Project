package model;
import java.util.UUID;
public class Responsible {
    private final String idAccount;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String domain;
    private String accountType;

    // Constructor
    public Responsible() {
        this.idAccount = UUID.randomUUID().toString();
        this.firstName = "unknown";
        this.lastName = "unknown";
        this.email = "unknown";
        this.phoneNumber = "unknown";
        this.domain = "unknown";
        this.accountType = "unknown";

    }
    public Responsible(String firstName, String lastName, String email,
                       String phoneNumber, String domain, String accountType) {
        this.idAccount=UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.accountType = accountType;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public Responsible(String idAccount, String firstName, String lastName, String email,
                       String phoneNumber, String domain, String accountType) {
        this.idAccount= idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.accountType = accountType;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }



    @Override
    public String toString() {
        return "Compte{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", domain='" + domain + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public String getString() {
        return
                ", idAccount='" + idAccount + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        ", domain='" + domain + '\'' +
                        ", accountType='" + accountType + '\''
                ;
    }
}
