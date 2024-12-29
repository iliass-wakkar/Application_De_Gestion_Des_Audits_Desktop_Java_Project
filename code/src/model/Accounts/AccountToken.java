package model.Accounts;

import java.util.UUID;

public class AccountToken {
    private final String token;
    private final String idAccount;
    private final String accountType;


    AccountToken(){
       token="unknown";
       idAccount="unknown";
       accountType="unknown";
    }
    public String getIdAccountToken() {
        return idAccount;
    }

    public AccountToken(String Token,String idAccount, String accountType) {
        this.token = Token;
        this.idAccount= idAccount;

        this.accountType = accountType;
    }

    public String getToken() {
        return token;
    }


    public String getAccountType() {
        return accountType;
    }


    @Override
    public String toString() {
        return "AccountToken{" +
                "idAccount='" + idAccount + '\'' +
                ", accountType='" + accountType + '\'' +
                ", token='" + token + '\'' +
                '}';
    }


}