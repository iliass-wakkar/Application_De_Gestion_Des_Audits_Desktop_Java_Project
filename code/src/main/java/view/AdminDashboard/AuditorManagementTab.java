package view.AdminDashboard;

import controller.AdminDashboard.AuditorManagementTabController;
import model.Accounts.Account;
import utils.AccountTableUtils;
import utils.ControllersGetter;
import view.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static utils.JsonFileHandler.ACCOUNTS_FILE_PATH;

public class AuditorManagementTab extends JPanel {

    private JButton createButton;
    private ButtonRenderer buttonRenderer = new ButtonRenderer();
    private List<Account> data = ControllersGetter.accountsController.getAccountsAuditor();
    private AuditorManagementTabController auditorManagementTabController;
    private static String[] formColumnNames = {"firstName", "lastName", "phoneNumber", "email", "password", "domain"};
    private JTable auditTable;
    private DefaultTableModel model;

    public AuditorManagementTab() {
        setUpUi();
        auditorManagementTabController = new AuditorManagementTabController(this);
    }


    public void refreshTable() {
        System.out.println("Refreshing table...");
        // Fetch the latest data
        data = ControllersGetter.accountsController.getAccountsAuditor();
        System.out.println(data);

        // Clear the existing table data
        model.setRowCount(0);

        // Add the new data to the table
        for (Account account : data) {
            Object[] rowData = {
                    account.getIdAccount(),
                    account.getFirstName(),
                    account.getLastName(),
                    account.getPhoneNumber(),
                    account.getEmail(),
                    account.getPassword(),
                    account.getDomain(),
                    "Actions" // Placeholder for the action buttons
            };
            model.addRow(rowData);
        }

        // Remove the "Actions" column
        TableColumn actionsColumn = auditTable.getColumnModel().getColumn(7);
        auditTable.removeColumn(actionsColumn);

        // Recreate the "Actions" column with a new ButtonRenderer and ButtonEditor
        actionsColumn = new TableColumn(7);
        actionsColumn.setHeaderValue("Actions");
        actionsColumn.setCellRenderer(new ButtonRenderer());
        actionsColumn.setCellEditor(new ButtonEditor(new JCheckBox(), auditTable, this::refreshTable));

        // Re-add the "Actions" column to the table
        auditTable.addColumn(actionsColumn);

        // Repaint the table to reflect the changes
        auditTable.repaint();
    }

    // Added file polling as an alternative
    private long lastModified = 0;

    private void startFilePoller() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Check every second
                    Path path = Paths.get(ACCOUNTS_FILE_PATH);
                    if (Files.exists(path)) {
                        long currentModified = Files.getLastModifiedTime(path).toMillis();
                        if (currentModified != lastModified) {
                            lastModified = currentModified;
                            System.out.println("File modified. Refreshing table...");
                            SwingUtilities.invokeLater(this::refreshTable);
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String[] getFormColumnNames() {
        return formColumnNames;
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

        // Create and return the table model
        model = new DefaultTableModel(AccountTableUtils.convertToTableData(data, columnNames), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the "Actions" column (column index 7) is editable
                return column == 7;
            }
        };

        auditTable = new JTable(model);
        auditTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        auditTable.setRowHeight(30);
        auditTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        auditTable.getTableHeader().setBackground(new Color(52, 73, 94)); // Dark blue header
        auditTable.getTableHeader().setForeground(Color.WHITE);
        auditTable.setFillsViewportHeight(true);

        // Add action buttons (Edit and Delete) to each row
        TableColumn actionsColumn = auditTable.getColumnModel().getColumn(7);
        actionsColumn.setCellRenderer(buttonRenderer);
        actionsColumn.setCellEditor(new ButtonEditor(new JCheckBox(), auditTable, this::refreshTable));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(auditTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);
    }

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