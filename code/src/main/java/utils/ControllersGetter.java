package utils;

import controller.AccountsController;
import model.Accounts.AccountToken;

public class ControllersGetter {
     public static AccountsController accountsController = new AccountsController();
     public static AccountToken currentAccountSession=null;
}
