package view.pages;

import controller.businessControllers.AuditsController;
import model.Accounts.Account;
import model.SystemManagement.Requirement;
import model.SystemManagement.Standard.Standard;
import model.audit.Audit;
import model.audit.RequirementStat;
import model.Organization.Organization;
import model.SystemManagement.ManagementSystem;
import utils.ControllersGetter;
import utils.PageSwitcher; // Import the PageSwitcher utility

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AuditViewDetails extends JPanel {

    private Audit audit;
    private Organization organization;
    private Account auditor;
    private ManagementSystem managementSystem;
    private String getBackPage;
    private AuditsController auditsController= new AuditsController();

    public AuditViewDetails() {
        // Default constructor
    }

    public AuditViewDetails(String idAudit, String getBackPage) {
        loadAuditDetails(idAudit, getBackPage);
    }

    public void loadAuditDetails(String idAudit, String getBackPage) {
        try {
            this.getBackPage = getBackPage;
            // Fetch audit details and related entities
            fetchData(idAudit);
            // Set up the UI
            setUpUi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading audit details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchData(String idAudit) throws Exception {
        // Fetch audit details
        audit = ControllersGetter.auditsController.getAuditById(idAudit);
        if (audit == null) {
            throw new Exception("Audit not found with ID: " + idAudit);
        }

        // Fetch related entities
        organization = ControllersGetter.organizationsController.getOrganizationById(audit.getIdOrganization());
        if (organization == null) {
            throw new Exception("Organization not found with ID: " + audit.getIdOrganization());
        }

        // Fetch auditor details with try-catch
        try {
            auditor = ControllersGetter.accountsController.getAuditorById(audit.getIdAuditor());
        } catch (Exception e) {
            throw new Exception("Error fetching auditor: " + e.getMessage());
        }

        managementSystem = ControllersGetter.organizationsController.getSystemManagementById(
                audit.getIdOrganization(), audit.getIdSystemManagement());
        System.out.println(managementSystem);
        if (managementSystem == null) {
            throw new Exception("Management System not found with ID: " + audit.getIdSystemManagement());
        }
    }

    private void setUpUi() {
        // Set the layout manager for the panel
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241)); // Light gray background

        // Create a main panel to hold all components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Apply border to mainPanel

        // Create a top panel for the dynamic button/label
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align button/label to the right
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add spacing below the button/label

        // Add dynamic button/label based on audit status
        String auditStatus = audit.getStatus(); // Get the audit status
        switch (auditStatus.toLowerCase()) {
            case "pending":
                addLaunchAuditButton(topPanel);
                break;
            case "in progress":
                addCompleteAuditButton(topPanel);
                break;
            case "completed":
                addCompletedLabel(topPanel);
                break;
            default:
                // Handle unknown status (optional)
                JLabel unknownLabel = new JLabel("Unknown Status: " + auditStatus);
                unknownLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                unknownLabel.setForeground(Color.RED);
                topPanel.add(unknownLabel);
                break;
        }

        // Add the top panel to the main panel
        mainPanel.add(topPanel);

        // Add audit details
        addSection(mainPanel, "Audit Details", new String[][]{
                {"ID", audit.getIdAudit()},
                {"Start Date", audit.getDateDebut()},
                {"Expiration Date", audit.getExpDate()},
                {"Subject", audit.getSubject()},
                {"Status", audit.getStatus()},
                {"Take Certificate", String.valueOf(audit.isTakeCertificate())},
                {"Is Pass", audit.getIsPass()}
        });

        // Add final report details
        addSection(mainPanel, "Final Report", new String[][]{
                {"Report ID", audit.getFinalReport().getIdRapport()},
                {"Report URL", audit.getFinalReport().getUrl()}
        });

        // Add organization details
        addSection(mainPanel, "Organization", new String[][]{
                {"ID", organization.getIdOrganization()},
                {"Name", organization.getName()},
                {"Description", organization.getDescription()}
        });

        // Add auditor details
        addSection(mainPanel, "Auditor", new String[][]{
                {"ID", auditor.getIdAccount()},
                {"Name", auditor.getFirstName() + " " + auditor.getLastName()},
                {"Email", auditor.getEmail()}
        });

        // Add management system details
        addSection(mainPanel, "Management System", new String[][]{
                {"ID", managementSystem.getIdManagementSystem()},
                {"Description", managementSystem.getDescription()},
                {"Certificate", managementSystem.getCertificate()}
        });

        // Add requirements section
        if (managementSystem.getRequirements() != null && !managementSystem.getRequirements().isEmpty()) {
            addRequirementsSection(mainPanel, "Requirements", managementSystem.getRequirements());
        }

        // Add standards section
        if (managementSystem.getStandards() != null && !managementSystem.getStandards().isEmpty()) {
            addStandardsSection(mainPanel, "Standards", managementSystem.getStandards());
        }

        // Add the main panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Add a "Back" button at the bottom
        addBackButton();
    }


    private void refreshPage() {
        // Remove all components from the panel
        removeAll();
        // Revalidate and repaint the panel to reflect the changes
        revalidate();
        repaint();
        // Reload the audit details
        loadAuditDetails(audit.getIdAudit(), getBackPage);
    }


    private void addLaunchAuditButton(JPanel topPanel) {
        // Create the "Launch Audit" button
        JButton launchAuditButton = new JButton("Launch Audit");
        launchAuditButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        launchAuditButton.setBackground(new Color(52, 152, 219)); // Blue color
        launchAuditButton.setForeground(Color.WHITE);
        launchAuditButton.setFocusPainted(false);
        launchAuditButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        launchAuditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to the button
        launchAuditButton.addActionListener(e -> {
            // Define what happens when the button is clicked
            boolean auditLaunched = audit.setStatus("in progress");
            auditsController.saveAudits();
            if (auditLaunched) {
                JOptionPane.showMessageDialog(this, "Audit launched successfully!", "Audit Launched", JOptionPane.INFORMATION_MESSAGE);
                // Refresh the UI to reflect the new status
                refreshPage();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to launch audit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add the button to the top panel
        topPanel.add(launchAuditButton);
    }



    private void addCompleteAuditButton(JPanel topPanel) {
        // Create the "Complete Audit" button
        JButton completeAuditButton = new JButton("Complete Audit");
        completeAuditButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        completeAuditButton.setBackground(new Color(46, 204, 113)); // Green color
        completeAuditButton.setForeground(Color.WHITE);
        completeAuditButton.setFocusPainted(false);
        completeAuditButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        completeAuditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to the button
        completeAuditButton.addActionListener(e -> {
            // Define what happens when the button is clicked
            boolean auditCompleted = audit.setStatus("completed");
            auditsController.saveAudits();
            if (auditCompleted) {
                JOptionPane.showMessageDialog(this, "Audit completed successfully!", "Audit Completed", JOptionPane.INFORMATION_MESSAGE);
                // Refresh the UI to reflect the new status
                refreshPage();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to complete audit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add the button to the top panel
        topPanel.add(completeAuditButton);
    }



    private void addCompletedLabel(JPanel topPanel) {
        // Create the "Completed" label
        JLabel completedLabel = new JLabel("Completed");
        completedLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        completedLabel.setForeground(new Color(46, 204, 113)); // Green color
        completedLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add the label to the top panel
        topPanel.add(completedLabel);
    }

    private boolean launchAudit(String auditId) {
        // Add your logic here to launch the audit
        // Return true if successful, false otherwise
        return true; // Replace with actual logic
    }

    private boolean completeAudit(String auditId) {
        // Add your logic here to complete the audit
        // Return true if successful, false otherwise
        return true; // Replace with actual logic
    }

    private void addBackButton() {
        // Create a "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(52, 152, 219)); // Blue color
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to the button
        backButton.addActionListener(e -> {
            // Switch back to the main page using PageSwitcher
            PageSwitcher.switchPage(getBackPage);
        });

        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(236, 240, 241)); // Light gray background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
        buttonPanel.add(backButton);

        // Add the button panel to the bottom of the main panel
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addSection(JPanel parent, String title, String[][] data) {
        // Create a section panel
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add section title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(52, 73, 94)); // Dark blue text
        sectionPanel.add(titleLabel);

        // Add section data
        for (String[] row : data) {
            JLabel label = new JLabel(row[0] + ": " + row[1]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            label.setForeground(new Color(44, 62, 80)); // Dark blue text
            sectionPanel.add(label);
        }

        // Add the section panel to the parent panel
        parent.add(sectionPanel);
        parent.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between sections
    }

    private void addRequirementsSection(JPanel parent, String title, List<Requirement> requirements) {
        // Create a section panel
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add section title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(52, 73, 94)); // Dark blue text
        sectionPanel.add(titleLabel);

        // Add spacing after the title
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add a card for each requirement
        for (Requirement requirement : requirements) {
            JPanel cardPanel = createRequirementCard(requirement);
            sectionPanel.add(cardPanel);
            sectionPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between cards
        }

        // Add the section panel to the parent panel
        parent.add(sectionPanel);
        parent.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing after the section
    }

    private JPanel createRequirementCard(Requirement requirement) {
        // Create a card panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(new Color(249, 249, 249)); // Light gray background
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding
        ));

        // Add requirement ID
        JLabel idLabel = new JLabel("ID: " + requirement.getIdRequirement());
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        idLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(idLabel);

        // Add requirement name
        JLabel nameLabel = new JLabel("Name: " + requirement.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(nameLabel);

        // Add requirement description
        JLabel descriptionLabel = new JLabel("Description: " + requirement.getDescription());
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(descriptionLabel);

        return cardPanel;
    }
    private void addStandardsSection(JPanel parent, String title, List<Standard> standards) {
        // Create a section panel
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add section title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(52, 73, 94)); // Dark blue text
        sectionPanel.add(titleLabel);

        // Add spacing after the title
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add a card for each standard
        for (Standard standard : standards) {
            JPanel cardPanel = createStandardCard(standard);
            sectionPanel.add(cardPanel);
            sectionPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between cards
        }

        // Add the section panel to the parent panel
        parent.add(sectionPanel);
        parent.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing after the section
    }

    private JPanel createStandardCard(Standard standard) {
        // Create a card panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(new Color(249, 249, 249)); // Light gray background
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding
        ));

        // Add standard ID
        JLabel idLabel = new JLabel("ID: " + standard.getIdStandard());
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        idLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(idLabel);

        // Add standard name
        JLabel nameLabel = new JLabel("Name: " + standard.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(nameLabel);

        // Add standard description
        JLabel descriptionLabel = new JLabel("Description: " + standard.getDescription());
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(44, 62, 80)); // Dark blue text
        cardPanel.add(descriptionLabel);

        return cardPanel;
    }    public static void main(String[] args) {
        // Example usage
        JFrame frame = new JFrame("Audit Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); // Set the size of the frame
        frame.setLocationRelativeTo(null); // Center the frame

        // Create and add the AuditViewDetails panel to the frame
        AuditViewDetails auditViewDetails = new AuditViewDetails("665e8952-9b4a-4b5f-84e5-2b083b97242f", "MainPage");
        frame.add(auditViewDetails);

        // Display the frame
        frame.setVisible(true);
    }
}