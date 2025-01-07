package controller;

import model.Accounts.Account;
import view.AdminDashboard.AuditorManagementTab;
import view.AdminDashboard.FormDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AuditorManagementTabController {
    private AuditorManagementTab view;
    private FormDialog createAuditorForm = new FormDialog();
    private String[] columnNames = AuditorManagementTab.getColumnNames();

    public AuditorManagementTabController(AuditorManagementTab view) {
        this.view = view;
        initController();
    }

    private void initController() {
        addCreatAuditorButtonEvent();
    }
    private void addCreatAuditorButtonEvent() {
        view.getCreateButton().addActionListener(ActionEvent -> {
            createAuditorForm.creationForm("Creat Auditor", columnNames,null);

        });
    }

}
