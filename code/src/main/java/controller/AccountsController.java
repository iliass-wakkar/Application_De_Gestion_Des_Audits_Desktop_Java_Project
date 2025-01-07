package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Accounts.Account;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import  utils.JsonFileHandler;


public class AccountsController {
    private static final String ACCOUNTS_FILE_PATH = JsonFileHandler.ACCOUNTS_FILE_PATH;
    private static ArrayList<Account> accounts = new ArrayList<>();
   static public String ADMIN_ACCOUNT_TYPE="admin";
    static public String AUDITOR_ACCOUNT_TYPE="auditor";


    public AccountsController() {
        loadAccounts();
        System.out.println("Accounts loaded successfully."+accounts);
    }

    // Load accounts from the JSON file
    public void loadAccounts() {
        try {
            List<Account> loadedAccounts = JsonFileHandler.loadData(ACCOUNTS_FILE_PATH, new TypeReference<List<Account>>() {});
            accounts = new ArrayList<>(loadedAccounts);
            System.out.printf(accounts.size() + " accounts loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
    }

    // Save accounts to the JSON file
    public void saveAccounts() {
        try {
            JsonFileHandler.saveData(ACCOUNTS_FILE_PATH, accounts);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    // Add or edit an account
    public void editAccount(Account updatedAccount) {
        Optional<Account> existingAccount = accounts.stream()
                .filter(account -> account.getIdAccount().equals(updatedAccount.getIdAccount()))
                .findFirst();

        if (existingAccount.isPresent()) {
            accounts.remove(existingAccount.get());
            accounts.add(updatedAccount);
            System.out.println("Account updated successfully.");
        } else {
            accounts.add(updatedAccount);
            System.out.println("New account added successfully.");
        }

        saveAccounts();
    }


    public boolean deleteAccount(String idAccount) {
        Optional<Account> accountToDelete = accounts.stream()
                .filter(account -> account.getIdAccount().equals(idAccount))
                .findFirst();

        if (accountToDelete.isPresent()) {
            accounts.remove(accountToDelete.get());
            saveAccounts();
            System.out.println("Account deleted successfully.");
            return true;
        } else {
            System.out.println("Account not found.");
            return false;
        }
    }
    // Get an account by email
    public Account getAccount(String email, String password) {
        Optional<Account> account = accounts.stream()
                .filter(acc -> acc.getEmail().equals(email)
                && acc.getPassword().equals(password))
                .findFirst();
        return account.orElse(null);
    }
    public Account getAccountAdmin(String email, String password) {
        Optional<Account> account = accounts.stream()
                .filter(acc -> acc.getAccountType().equals(ADMIN_ACCOUNT_TYPE)
                        && acc.getEmail().equals(email)
                        && acc.getPassword().equals(password))
                .findFirst();
        return account.orElse(null);
    }


    public Account getAccountAuditor(String email, String password) {
        Optional<Account> account = accounts.stream()
                .filter(acc -> acc.getAccountType().equals(AUDITOR_ACCOUNT_TYPE)
                        && acc.getEmail().equals(email)
                        && acc.getPassword().equals(password))
                .findFirst();
        return account.orElse(null);
    }


    // Get all accounts
    public List<Account> getAccounts() {
        return accounts;
    }
    // Get all accounts
    public List<Account> getAccountsAdmin() {
        return accounts.stream().filter(acc->acc.getAccountType().equals(ADMIN_ACCOUNT_TYPE)).toList();
    }
    // Get all accounts
    public List<Account> getAccountsAuditor() {
        return accounts.stream().filter(acc->acc.getAccountType().equals(AUDITOR_ACCOUNT_TYPE)).toList();
    }
}
