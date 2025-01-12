package utils;

import controller.businessControllers.account.AccountsController;
import controller.businessControllers.organization.OrganizationController;
import model.Accounts.AccountToken;

public class ControllersGetter {
     public static AccountsController accountsController = new AccountsController();
     public static AccountToken currentAccountSession=null;
     public static OrganizationController organizationController = new OrganizationController();


}
