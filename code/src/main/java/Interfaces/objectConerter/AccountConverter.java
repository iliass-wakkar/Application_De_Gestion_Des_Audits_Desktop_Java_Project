package Interfaces.objectConerter;

import model.Accounts.Account;

public class AccountConverter implements ObjectConverter<Account> {
    @Override
    public Account convertObject(){
        return new Account();
    }
}
