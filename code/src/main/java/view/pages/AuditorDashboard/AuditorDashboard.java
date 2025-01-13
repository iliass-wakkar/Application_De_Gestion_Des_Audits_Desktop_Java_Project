package view.pages.AuditorDashboard;


import controller.uiControllers.AuditorDashboard.AuditorDashboardController;
import model.SystemManagement.ManagementSystem;


import javax.swing.*;
import java.awt.*;


public class AuditorDashboard extends JPanel {

    private AuditorDashboardController auditorDashboardController;

    public JButton getLogoutButton() {
        return logoutButton;
    }
    private  AuditorRequirementManagementTab auditorRequirementManagementTab = new AuditorRequirementManagementTab();
    private ManagementSystemAuditorTab managementSystemAuditorTab = new ManagementSystemAuditorTab();

    private JButton logoutButton;

    public AuditorDashboard() {
        setUpUi();
        auditorDashboardController = new AuditorDashboardController(this);
    }

    void setUpUi() {
        // Set the layout manager for the panel
        this.setLayout(new BorderLayout());

        // Create a top panel for the title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 0, 0)); // Black background
        topPanel.setPreferredSize(new Dimension(getWidth(), 80));

        // Add the title
        JLabel titleLabel = new JLabel("Auditor Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.WEST);

        // Add the logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 69, 0)); // Red color for logout
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        topPanel.add(logoutButton, BorderLayout.EAST);

        // Create a tabbed pane for the dashboard
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));

        // Add tabs
        tabbedPane.addTab("Audits Management", createTabPanel("Audits Management"));
        tabbedPane.addTab("Requirements Management", auditorRequirementManagementTab);
        tabbedPane.addTab("System Management", managementSystemAuditorTab);

        // Add panels to the main panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createTabPanel(String tabName) {
        JPanel tabPanel = new JPanel(new BorderLayout());
        tabPanel.setBackground(new Color(240, 248, 255)); // Alice Blue background

        // Add content to the tab (customize as needed)
        JLabel label = new JLabel("Welcome to " + tabName);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        tabPanel.add(label, BorderLayout.CENTER);

        return tabPanel;
    }

    public static void main(String[] args) {
        // Create a JFrame to display the AuditorDashboard
        JFrame frame = new JFrame("Auditor Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame

        // Add the AuditorDashboard panel to the frame
        AuditorDashboard auditorDashboard = new AuditorDashboard();
        frame.add(auditorDashboard);

        // Display the frame
        frame.setVisible(true);
    }
}