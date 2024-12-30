package controller;

import model.Accounts.Account;
import utils.PageSwitcher;
import utils.TokenHandler;
import view.LoginPage;


import javax.swing.*;
import utils.ControllersGetter;

public class LoginPageController {
    private final LoginPage view;

    public LoginPageController(LoginPage loginPage) {
        this.view = loginPage;
        initLoginPageEvents();
    }

    private void initLoginPageEvents() {
        view.getLoginButton().addActionListener(e -> handleLoginEvent(
                view.getEmailField().getText(),
                new String(view.getPasswordField().getPassword())
        ));
    }

    public void handleLoginEvent(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email and Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform login logic, e.g., checking credentials

        Account account = ControllersGetter.accountsController.getAccountAdmin(email,password);

        if (account != null) {
            AccountSessionHandler.UpdateCurrentAccountSession(account.getIdAccount(),account.getAccountType());
            PageSwitcher.switchPage("home");
        } else {
            JOptionPane.showMessageDialog(view, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
