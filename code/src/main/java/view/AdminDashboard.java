package view;

import controller.AdminDashboardController;
import utils.PageSwitcher;
import view.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JPanel {

    private AdminDashboardController adminDashboardController;
    private JButton logoutButton;

    public AdminDashboard() {
        setUpUi();
        adminDashboardController = new AdminDashboardController(this);
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    void setUpUi() {
        // Set the layout manager for the panel
        this.setLayout(new BorderLayout());

        // Create a top panel for the title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 0, 0)); // Black background
        topPanel.setPreferredSize(new Dimension(getWidth(), 80));

        // Add the title
        JLabel titleLabel = new JLabel("Admin Dashboard");
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle logout
                PageSwitcher.switchPage("login");
            }
        });
        topPanel.add(logoutButton, BorderLayout.EAST);

        // Create a tabbed pane for the dashboard
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));

        // Add tabs
        tabbedPane.addTab("Auditor Management", createTabPanel("Auditor Management"));
        tabbedPane.addTab("Clause Management", createTabPanel("Clause Management"));
        tabbedPane.addTab("Requirements Management", createTabPanel("Requirements Management"));
        tabbedPane.addTab("Organization Management", createTabPanel("Organization Management"));
        tabbedPane.addTab("System Management", createTabPanel("System Management"));

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
        // Create a JFrame to display the AdminDashboard
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame

        // Add the AdminDashboard panel to the frame
        AdminDashboard adminDashboard = new AdminDashboard();
        frame.add(adminDashboard);

        // Display the frame
        frame.setVisible(true);
    }
}