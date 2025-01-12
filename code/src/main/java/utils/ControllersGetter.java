package utils;

import controller.businessControllers.ResponsiblesController;
import controller.businessControllers.account.AccountsController;
import controller.businessControllers.organization.OrganizationsController;
import model.Accounts.AccountToken;

public class ControllersGetter {
     public static AccountsController accountsController = new AccountsController();
     public static AccountToken currentAccountSession=null;
     public static OrganizationsController organizationsController = new OrganizationsController();
     public static ResponsiblesController responsiblesController = new ResponsiblesController();


}
