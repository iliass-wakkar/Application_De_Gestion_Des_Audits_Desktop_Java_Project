package view.AdminDashboard;

import controller.AuditorManagementTabController;
import model.Accounts.Account;
import utils.AccountTableUtils;
import utils.ControllersGetter;
import view.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AuditorManagementTab extends JPanel {

    private JButton createButton ;
    private ButtonRenderer buttonRenderer= new ButtonRenderer();
    private List<Account> data = ControllersGetter.accountsController.getAccountsAuditor();
    private AuditorManagementTabController auditorManagementTabController;
    private static String[] columnNames = { "firstName", "lastName", "phoneNumber", "email", "password", "domain"};

    public AuditorManagementTab() {
        setUpUi();
        auditorManagementTabController = new AuditorManagementTabController(this);
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getEditeButton() {
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
        createButton = new JButton("Create New Audit");
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
            String[] columnNames = {"idAccount", "firstName", "lastName", "phoneNumber", "email", "password", "domain", "Actions"};

            Object[][] tableData = AccountTableUtils.convertToTableData(data, columnNames);


        // Create and return the table model
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Only the "Actions" column (column index 7) is editable
                    return column == 7;
                }
            };

        JTable auditTable = new JTable(model);
        auditTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        auditTable.setRowHeight(30);
        auditTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        auditTable.getTableHeader().setBackground(new Color(52, 73, 94)); // Dark blue header
        auditTable.getTableHeader().setForeground(Color.WHITE);
        auditTable.setFillsViewportHeight(true);

        // Add action buttons (Edit and Delete) to each row
        TableColumn actionsColumn = auditTable.getColumnModel().getColumn(7);
        actionsColumn.setCellRenderer(buttonRenderer);
        actionsColumn.setCellEditor(new ButtonEditor(new JCheckBox(), auditTable));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(auditTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Custom renderer for buttons in the table


    // Main method to test the AuditorManagementTab
    public static void main(String[] args) {
        // Create a JFrame to display the AuditorManagementTab
        JFrame frame = new JFrame("Auditor Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null); // Center the frame

        // Add the AuditorManagementTab panel to the frame
        AuditorManagementTab auditorManagementTab = new AuditorManagementTab();
        frame.add(auditorManagementTab);

        // Display the frame
        frame.setVisible(true);
    }
}