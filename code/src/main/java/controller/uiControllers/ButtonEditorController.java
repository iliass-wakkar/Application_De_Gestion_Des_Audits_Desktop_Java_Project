package controller.uiControllers;

import utils.interfaces.IButtonEditorEventsHandler;
import utils.interfaces.IFormDialogEventHandler;
import view.pages.AdminDashboard.AuditorManagementTab;
import view.pages.AdminDashboard.ButtonEditor;
import view.pages.AdminDashboard.FormDialog;

public class ButtonEditorController {
    private ButtonEditor view;
    private FormDialog createAuditorForm;

    private  IFormDialogEventHandler iFormDialogEditEventHandler;
    private  IFormDialogEventHandler iFormDialogDeleteEventHandler;
    private IButtonEditorEventsHandler iButtonEditorEditEventHandler;
    public ButtonEditorController(ButtonEditor view, IButtonEditorEventsHandler iButtonEditorEventsHandler) {
        this.view = view;
        this.iButtonEditorEditEventHandler = iButtonEditorEventsHandler;

        controllers();
    }
    public void controllers(){
        addEditAuditorButtonEvent();
        addDeleteAuditorButtonEvent();
    }

    private void addEditAuditorButtonEvent() {

        view.getEditButton().addActionListener(ActionEvent -> {
           String[] columnNames = AuditorManagementTab.getColumnNamesCreateEdit();
//            createAuditorForm =  new FormDialog(" Edit",columnNames,view.getRowData(), iFormDialogEditEventHandler);

            iButtonEditorEditEventHandler.editObjectEventHandler(view);

        });
    }
    private void addDeleteAuditorButtonEvent() {


        view.getDeleteButton().addActionListener(ActionEvent -> {
            String[]   columnNames = AuditorManagementTab.getColumnNamesCreateEdit();

//            JFrame parentFrame = new JFrame("Edit auditor");
//            Object[] Column = view.getRowData();

            iButtonEditorEditEventHandler.deleteObjectEventHandler(view);

        });
    }
}
