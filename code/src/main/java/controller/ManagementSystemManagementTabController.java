package controller;

import model.SystemManagement.ManagementSystem;
import utils.ControllersGetter;
import utils.SaveUtil;
import utils.interfaces.IButtonEditorEventsHandler;
import utils.interfaces.IFormDialogEventHandler;
import utils.interfaces.objectConverter.ManagementSystemConverter;
import view.AdminDashboard.ButtonEditor;
import view.AdminDashboard.FormDialog;
import view.AdminDashboard.ManagementSystemManagementTab;

import javax.swing.*;

public class ManagementSystemManagementTabController {
    private ManagementSystemManagementTab view;
    private FormDialog createManagementSystemForm;
    private FormDialog editManagementSystemForm;
    private String[] columnNames = ManagementSystemManagementTab.getColumnNamesCreateEdit();
    private SaveUtil<ManagementSystem> saveUtil = new SaveUtil(new ManagementSystemConverter());

    public ManagementSystemManagementTabController(ManagementSystemManagementTab view) {
        this.view = view;
        initController();
    }

    private void initController() {
        addCreateManagementSystemButtonEvent();
    }

    private void addCreateManagementSystemButtonEvent() {
        view.getCreateButton().addActionListener(ActionEvent -> {
            createManagementSystemForm = new FormDialog("Create Management System", columnNames, saveCreateManagementSystemIFormEventHandler);
        });
    }

    private IFormDialogEventHandler saveEditManagementSystemIFormEventHandler = (formDialog) -> {
        try {
            if (formDialog.validateForm()) {
                ManagementSystem managementSystem = saveUtil.saveFormData(formDialog.getFormData());
                String idOrg = managementSystem.getIdOrg(); // Get the organization ID
                ControllersGetter.organizationController.editManagementSystemInOrganization(idOrg, managementSystem.getIdManagementSystem(), managementSystem);
                view.refreshTable();
                JOptionPane.showMessageDialog(
                        editManagementSystemForm,
                        "Management System updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                formDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        formDialog,
                        "Please fill in all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    editManagementSystemForm,
                    "An error occurred: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    };

    private IFormDialogEventHandler saveCreateManagementSystemIFormEventHandler = (formDialog) -> {
        System.out.println("ManagementSystemManagementTabController saveCreateManagementSystemIFormEventHandler");
        try {
            if (formDialog.validateForm()) {
                ManagementSystem managementSystem = saveUtil.saveFormData(formDialog.getFormData());
                String idOrg = managementSystem.getIdOrg(); // Get the organization ID
                ControllersGetter.organizationController.addManagementSystemToOrganization(idOrg, managementSystem);
                JOptionPane.showMessageDialog(
                        formDialog,
                        "New Management System added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                view.refreshTable();
                formDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        formDialog,
                        "Please fill in all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    formDialog,
                    "An error occurred: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    };

    private IButtonEditorEventsHandler iButtonEditorEventsHandler = new IButtonEditorEventsHandler() {
        @Override
        public void editObjectEventHandler(ButtonEditor view) {
            String[] columnNames = ManagementSystemManagementTab.getColumnNamesCreateEdit();
            editManagementSystemForm = new FormDialog("Edit", columnNames, view.getRowData(), saveEditManagementSystemIFormEventHandler, view.getId());
        }
        @Override
        public void deleteObjectEventHandler(ButtonEditor buttonEditorView) {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this Management System?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (response == JOptionPane.YES_OPTION) {
                String idOrg = buttonEditorView.getRowData()[1].toString(); // Get the organization ID
                ControllersGetter.organizationController.deleteManagementSystemFromOrganization(idOrg, buttonEditorView.getId());
                view.refreshTable();
                System.out.println("Deleting Management System");
            } else {
                System.out.println("Deleting operation canceled.");
            }
        }
    };

    public IButtonEditorEventsHandler getIButtonEditorEventsHandler() {
        return iButtonEditorEventsHandler;
    }
}