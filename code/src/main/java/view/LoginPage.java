package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  controller.LoginPageController;


public class LoginPage extends JPanel {
    private final LoginPageController loginPageController;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel welcomeLabel;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {
        setUpUi();
        loginPageController = new LoginPageController(this);
    }

    void setUpUi() {
        // Set the layout manager for the panel
        this.setLayout(new BorderLayout());

        // Create a panel to hold the form elements
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Welcome label
        welcomeLabel = new JLabel("Welcome! Please log in.");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Bold and larger font
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align in the box layout

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Consistent height
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Consistent height
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Login button
        loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180)); // Light blue background
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the form panel
        formPanel.add(welcomeLabel);
        formPanel.add(Box.createVerticalStrut(20)); // Spacing

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10)); // Spacing

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20)); // Spacing

        formPanel.add(loginButton);

        // Add the form panel to the center of the main panel
        this.add(formPanel, BorderLayout.CENTER);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

}
