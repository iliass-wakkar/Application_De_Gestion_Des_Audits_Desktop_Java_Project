package controller;

import view.AdminDashboard.AuditorManagementTab;
import view.AdminDashboard.ButtonEditor;
import view.AdminDashboard.FormDialog;

import javax.swing.*;

public class ButtonEditorController {
    private ButtonEditor view;
    private FormDialog createAuditorForm = new FormDialog();
    private String[] columnNames;
    public ButtonEditorController(ButtonEditor view) {
        this.view = view;
    }
    public void controllers(){
        addEditAuditorButtonEvent();
        addDeleteAuditorButtonEvent();
    }
    private void addEditAuditorButtonEvent() {
         columnNames = AuditorManagementTab.getColumnNames();

        view.getEditButton().addActionListener(ActionEvent -> {

            JFrame parentFrame = new JFrame("Edit auditor");
            // Center the frame on the screen
            // Create an instance of the CreateAuditorForm
            createAuditorForm.creationForm("Creat Auditor", columnNames,view.getRowData());

        });
    }
    private void addDeleteAuditorButtonEvent() {
        columnNames = AuditorManagementTab.getColumnNames();

        view.getEditButton().addActionListener(ActionEvent -> {

            JFrame parentFrame = new JFrame("Edit auditor");
            Object[] Column = view.getRowData();

        });
    }
}
