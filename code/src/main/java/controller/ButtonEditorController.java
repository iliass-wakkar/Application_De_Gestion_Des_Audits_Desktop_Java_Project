package controller;

import view.AdminDashboard.AuditorManagementTab;
import view.AdminDashboard.ButtonEditor;
import view.AdminDashboard.FormDialog;

public class ButtonEditorController {
    private ButtonEditor view;
    private FormDialog createAuditorForm ;
    private String[] columnNames;
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

        view.getEditButton().addActionListener(ActionEvent -> {

            createAuditorForm=new FormDialog("Edit Auditor", columnNames,view.getRowData());

        });
    }
    private void addDeleteAuditorButtonEvent() {
        columnNames = AuditorManagementTab.getFormColumnNames();

        view.getEditButton().addActionListener(ActionEvent -> {

            Object[] Column = view.getRowData();

        });
    }
}
