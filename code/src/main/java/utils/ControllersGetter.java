package utils;

import controller.AccountsController;
import controller.OrganizationController;
import model.Accounts.AccountToken;

public class ControllersGetter {
     public static AccountsController accountsController = new AccountsController();
     public static AccountToken currentAccountSession=null;
     public static OrganizationController organizationController = new OrganizationController();


}
