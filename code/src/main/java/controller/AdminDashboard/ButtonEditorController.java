package controller.AdminDashboard;

import Interfaces.objectConerter.AccountConverter;
import model.Accounts.Account;
import utils.SaveUtil;
import view.AdminDashboard.AuditorManagementTab;
import view.AdminDashboard.ButtonEditor;
import view.AdminDashboard.FormDialog;

import javax.swing.*;

import java.util.Arrays;

import static utils.ControllersGetter.accountsController;

public class ButtonEditorController {
    private ButtonEditor view;
    private FormDialog editAuditorForm ;
    private String[] columnNames;
    private SaveUtil<Account> saveUtil = new SaveUtil(new AccountConverter());

    public ButtonEditorController(ButtonEditor view) {
        this.view = view;
        controllers();
    }
    public void controllers(){
        addEditAuditorButtonEvent();
        addDeleteAuditorButtonEvent();
    }
    private void addEditAuditorButtonEvent() {
         columnNames = AuditorManagementTab.getFormColumnNames();

        view.getEditButton().addActionListener(showForm -> {

            editAuditorForm=new FormDialog("Edit Auditor", columnNames,view.getRowData());
            System.out.println("dhmbhsmbjkb j"+Arrays.toString(view.getRowData()));
            editAuditorForm.getSaveButton().addActionListener(saveButton -> {
                try {
                    if (editAuditorForm.validateForm()) {

                        Account account = saveUtil.saveFormData(editAuditorForm.getFormData()); // Save form data
                        account.setAccountType("auditor");
                        accountsController.editAccount(view.getId(),account); // Create account using AccountsController
                        view.getRefreshCallback().run();
                        // Show success message
                        JOptionPane.showMessageDialog(
                                editAuditorForm,
                                "New Auditor added successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Refresh the table to show the new data

                        editAuditorForm.dispose(); // Close the form dialog
                    } else {
                        JOptionPane.showMessageDialog(
                                editAuditorForm,
                                "Please fill in all fields.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            editAuditorForm,
                            "An error occurred: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });

            editAuditorForm.setVisible(true);
        });

    }

    private void addDeleteAuditorButtonEvent() {
        view.getDeleteButton().addActionListener(ActionEvent -> {

            // Show a confirmation dialog
            int response = JOptionPane.showConfirmDialog(
                    null, // No parent component
                    "Are you sure you want to delete this auditor?", // Message
                    "Confirm Delete", // Dialog title
                    JOptionPane.YES_NO_OPTION // Option type (Yes/No)
            );

            // Check the user's response
            if (response == JOptionPane.YES_OPTION) {
                accountsController.deleteAccount(view.getId());
                view.getRefreshCallback().run();
                System.out.println("Deleting auditor ");
            } else {
                // User clicked "No" or closed the dialog, do nothing
                System.out.println("Deleting operation canceled.");
            }
        });
    }
}
