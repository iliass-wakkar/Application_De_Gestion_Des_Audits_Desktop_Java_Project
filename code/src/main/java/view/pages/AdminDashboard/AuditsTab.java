package view.pages.AdminDashboard;

import controller.uiControllers.adminDashboard.Tabs.AuditsTabController;
import model.audit.Audit;
import utils.TableConverterUtility;
import utils.ControllersGetter;
import view.components.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class AuditsTab extends JPanel {

    private JButton createButton = new JButton("Create New Audit");
    private ButtonRenderer buttonRenderer = new ButtonRenderer();
    private List<Audit> data;
    private AuditsTabController auditsTabController;
    private static String[] columnNamesCreateEdit = {"IdAudit", "DateDebut", "ExpDate", "Subject", "Status", "IdAuditor", "IdOrganization", "IdSystemManagement"};
    DefaultTableModel model;
    JTable auditsTable;

    public AuditsTab() {
        this.data = ControllersGetter.auditsController.getAllAudits(); // Get all audits
        auditsTabController = new AuditsTabController(this);
        setUpUi();
    }

    public static String[] getColumnNamesCreateEdit() {
        return columnNamesCreateEdit;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getEditButton() {
        return buttonRenderer.getEditButton();
    }

    public JButton getDeleteButton() {
        return buttonRenderer.getDeleteButton();
    }

    private void setUpUi() {
        // Set the layout manager for the panel
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(236, 240, 241)); // Light gray background
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a button panel at the top
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(236, 240, 241)); // Light gray background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Add "Create" button
        createButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        createButton.setBackground(new Color(52, 152, 219)); // Blue color
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(createButton);

        // Add the button panel to the top of the tab
        this.add(buttonPanel, BorderLayout.NORTH);

        // Define column names
        String[] columnNames = {"IdAudit", "DateDebut", "ExpDate", "Subject", "Status", "IdAuditor", "IdOrganization", "IdSystemManagement", "Actions"};

        Object[][] tableData = TableConverterUtility.convertToTableData(data, columnNames);

        // Create and return the table model
        model = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the "Actions" column (column index 8) is editable {accept event}
                return column == 8;
            }
        };

        auditsTable = new JTable(model);
        auditsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        auditsTable.setRowHeight(30);
        auditsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        auditsTable.getTableHeader().setBackground(new Color(52, 73, 94)); // Dark blue header
        auditsTable.getTableHeader().setForeground(Color.WHITE);
        auditsTable.setFillsViewportHeight(true);

        // Add action buttons (Edit and Delete) to each row
        TableColumn actionsColumn = auditsTable.getColumnModel().getColumn(8);
        actionsColumn.setCellRenderer(buttonRenderer);
        actionsColumn.setCellEditor(new ButtonEditor(new JCheckBox(), auditsTable, auditsTabController.getIButtonEditorEventsHandler()));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(auditsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshTable() {
        // Fetch the latest data
        data = ControllersGetter.auditsController.getAllAudits();
        System.out.println(data);
        // Clear the existing table data
        model.setRowCount(0);

        // Add the new data to the table
        for (Audit audit : data) {
            Object[] rowData = {
                    audit.getIdAudit(),
                    audit.getDateDebut(),
                    audit.getExpDate(),
                    audit.getSubject(),
                    audit.getStatus(),
                    audit.getIdAuditor(),
                    audit.getIdOrganization(),
                    audit.getIdSystemManagement(),
                    "Actions" // Placeholder for the action buttons
            };
            model.addRow(rowData);
        }

        TableColumn actionsColumn = auditsTable.getColumnModel().getColumn(8);
        auditsTable.removeColumn(actionsColumn);

        // Recreate the "Actions" column with a new ButtonRenderer and ButtonEditor
        actionsColumn = new TableColumn(8);
        actionsColumn.setHeaderValue("Actions");
        actionsColumn.setCellRenderer(new ButtonRenderer());
        actionsColumn.setCellEditor(new ButtonEditor(new JCheckBox(), auditsTable, auditsTabController.getIButtonEditorEventsHandler()));

        // Re-add the "Actions" column to the table
        auditsTable.addColumn(actionsColumn);

        // Repaint the table to reflect the changes
        auditsTable.repaint();
    }

    public static void main(String[] args) {
        // Create a JFrame to display the AuditsTab
        JFrame frame = new JFrame("Audits Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null); // Center the frame

        // Add the AuditsTab panel to the frame
        AuditsTab auditsTab = new AuditsTab();
        frame.add(auditsTab);

        // Display the frame
        frame.setVisible(true);
    }
}