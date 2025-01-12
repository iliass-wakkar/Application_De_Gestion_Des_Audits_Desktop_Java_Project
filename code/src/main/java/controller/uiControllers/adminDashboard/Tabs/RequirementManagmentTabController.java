package controller.uiControllers.adminDashboard.Tabs;

import model.SystemManagement.Requirement;
import utils.ControllersGetter;
import utils.SaveUtil;
import utils.interfaces.IButtonEditorEventsHandler;
import utils.interfaces.IFormDialogEventHandler;
import view.pages.AdminDashboard.ButtonEditor;
import view.pages.AdminDashboard.FormDialog;
import view.pages.AdminDashboard.RequirementManagmentTab;

import javax.swing.*;

public class RequirementManagmentTabController {
    private RequirementManagmentTab view;
    private FormDialog createRequirementForm;
    private FormDialog editRequirementForm;
    private String[] columnNames = RequirementManagmentTab.getColumnNamesCreateEdit();
    private SaveUtil<Requirement> saveUtil = new SaveUtil<>(Requirement::new);

    public RequirementManagmentTabController(RequirementManagmentTab view) {
        this.view = view;
        initController();
    }

    private void initController() {
        addCreateRequirementButtonEvent();
    }

    private void addCreateRequirementButtonEvent() {
        view.getCreateButton().addActionListener(ActionEvent -> {
            createRequirementForm = new FormDialog("Create Requirement", columnNames, saveCreateRequirementIFormEventHandler);
        });
    }

    private IFormDialogEventHandler saveEditRequirementIFormEventHandler = (formDialog) -> {
        try {
            if (formDialog.validateForm()) {
                Requirement requirement = saveUtil.saveFormData(formDialog.getFormData());
                String idOrg = requirement.getIdOrganization();
                String idMS = requirement.getIdManagmentSystem(); // Get the management system ID
                ControllersGetter.organizationsController.editSystemManagementRequirementById(idOrg, idMS, formDialog.getId(), requirement);
                view.refreshTable();
                JOptionPane.showMessageDialog(
                        editRequirementForm,
                        "Requirement updated successfully!",
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
                    editRequirementForm,
                    "An error occurred: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    };

    private IFormDialogEventHandler saveCreateRequirementIFormEventHandler = (formDialog) -> {
        try {
            if (formDialog.validateForm()) {
                Requirement requirement = saveUtil.saveFormData(formDialog.getFormData());
                String idOrg = requirement.getIdOrganization();
                String idMS = requirement.getIdManagmentSystem(); // Get the management system ID
                ControllersGetter.organizationsController.createSystemManagementRequirement(idOrg, idMS, requirement);
                JOptionPane.showMessageDialog(
                        formDialog,
                        "New Requirement added successfully!",
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
            String[] columnNames = RequirementManagmentTab.getColumnNamesCreateEdit();
            editRequirementForm = new FormDialog("Edit Requirement", columnNames, view.getRowData(), saveEditRequirementIFormEventHandler, view.getId());
        }

        @Override
        public void deleteObjectEventHandler(ButtonEditor buttonEditorView) {
            try {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this Requirement?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
                    String idOrg = buttonEditorView.getRowData()[0].toString();
                    String idMS = buttonEditorView.getRowData()[1].toString(); // Get the management system ID
                    ControllersGetter.organizationsController.deleteSystemManagementRequirementById(idOrg, idMS, buttonEditorView.getId());
                    view.refreshTable();
                    System.out.println("Deleting Requirement");
                } else {
                    System.out.println("Deleting operation canceled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    };

    public IButtonEditorEventsHandler getIButtonEditorEventsHandler() {
        return iButtonEditorEventsHandler;
    }
}