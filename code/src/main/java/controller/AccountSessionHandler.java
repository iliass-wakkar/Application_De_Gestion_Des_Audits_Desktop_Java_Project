package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Accounts.AccountToken;
import utils.ControllersGetter;
import utils.JsonFileHandler;
import utils.PageSwitcher;
import utils.TokenHandler;

import javax.naming.ldap.Control;
import java.io.IOException;

public class AccountSessionHandler {
  static    public  void loadCurrentAccountSession() {

      try {
           ControllersGetter.currentAccountSession   = JsonFileHandler.loadDataObject(JsonFileHandler.ACCOUNTS_SESSION_FILE_PATH, new TypeReference<AccountToken>() {});
//  to do check token :


          System.out.println( "the current account session is : "+ControllersGetter.currentAccountSession);
           if(ControllersGetter.currentAccountSession==null || !TokenHandler.checkToken(ControllersGetter.currentAccountSession.getToken())) {
               ClearCurrentAccountSession();
           }



        } catch (IOException e) {
            System.err.println("Error loading current Session: " + e.getMessage());
            ControllersGetter.currentAccountSession=null;
        }
    }
  static   public void UpdateCurrentAccountSession(String accountId, String accountType ) {
        ControllersGetter.currentAccountSession= TokenHandler.generateToken( accountId, accountType);
        try {
            JsonFileHandler.saveDataObject(JsonFileHandler.ACCOUNTS_SESSION_FILE_PATH,ControllersGetter.currentAccountSession);
        } catch (IOException e) {
            System.err.println("Error Saving current Session: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    static   public void ClearCurrentAccountSession( ) {
        ControllersGetter.currentAccountSession= null;
        PageSwitcher.switchPage("login");
        try {
            JsonFileHandler.saveDataObject(JsonFileHandler.ACCOUNTS_SESSION_FILE_PATH,ControllersGetter.currentAccountSession);
        } catch (IOException e) {
            System.err.println("Error Saving current Session: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
