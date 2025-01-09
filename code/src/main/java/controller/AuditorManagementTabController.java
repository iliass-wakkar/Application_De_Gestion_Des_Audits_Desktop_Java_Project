package controller;

import Interfaces.objectConerter.AccountConverter;
import Interfaces.objectConerter.ObjectConverter;
import model.Accounts.Account;
import utils.SaveUtil;
import view.AdminDashboard.AuditorManagementTab;
import view.AdminDashboard.FormDialog;

import javax.swing.*;

public class AuditorManagementTabController {
    private AuditorManagementTab view;
    private FormDialog createAuditorForm;
    private String[] columnNames = AuditorManagementTab.getFormColumnNames();
    private SaveUtil<Account> saveUtil = new SaveUtil(new AccountConverter());
    private AccountsController accountsController = new AccountsController();

    public AuditorManagementTabController(AuditorManagementTab view) {
        this.view = view;
        initController();
    }

    private void initController() {
        addCreatAuditorButtonEvent();
    }

    private void addCreatAuditorButtonEvent() {
        view.getCreateButton().addActionListener(addButton -> {
            createAuditorForm = new FormDialog("Create Auditor", columnNames, null);

            createAuditorForm.getSaveButton().addActionListener(saveButton -> {
                try {
                    if (createAuditorForm.validateForm()) {
                        Account account = saveUtil.saveFormData(createAuditorForm.getFormData()); // Save form data
                        account.setAccountType("auditor");
                        accountsController.creatAccount(account); // Create account using AccountsController

                        // Show success message
                        JOptionPane.showMessageDialog(
                                createAuditorForm,
                                "New Auditor added successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Refresh the table to show the new data
                        view.refreshTable();

                        createAuditorForm.dispose(); // Close the form dialog
                    } else {
                        JOptionPane.showMessageDialog(
                                createAuditorForm,
                                "Please fill in all fields.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            createAuditorForm,
                            "An error occurred: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });

            // Add a cancel button listener to close the form
            createAuditorForm.getCancelButton().addActionListener(cancelButton -> {
                createAuditorForm.dispose(); // Close the form dialog
            });

            createAuditorForm.setVisible(true);
        });
    }
}