package view.AdminDashboard;

import model.Accounts.Account;
import utils.AccountTableUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FormDialog<T> extends JDialog {
    public void creationForm(String title, String[] fieldNames, Object[] data) {
        // Create a new JDialog for the form
        JDialog dialog = new JDialog((JFrame) null, title, true); // Modal dialog
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog on the screen
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(236, 240, 241)); // Light gray background

        // Create a map to store the text fields
        Map<String, JTextField> fields = new HashMap<>();

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(fieldNames.length + 1, 2, 10, 10)); // +1 for buttons
        formPanel.setBackground(new Color(236, 240, 241));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add fields dynamically based on fieldNames
        for (int i = 0; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];

            // Create a label for the field
            JLabel label = new JLabel(fieldName + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(new Color(52, 73, 94)); // Dark blue text
            formPanel.add(label);

            // Create a text field for user input
            JTextField textField = new JTextField();
            textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1), // Light gray border
                    BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding
            ));

            // If data is provided and has a value for this field, pre-fill the text field
            if (data != null && i < data.length && data[i] != null) {
                textField.setText(data[i].toString());
            }

            fields.put(fieldName, textField); // Store the field in the map
            formPanel.add(textField);
        }

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(52, 152, 219)); // Blue color
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            // Collect data from the form
            Map<String, String> formData = new HashMap<>();
            for (String fieldName : fieldNames) {
                formData.put(fieldName, fields.get(fieldName).getText());
            }
            System.out.println("Form data: " + formData); // Print the data (or process it as needed)
            dialog.dispose(); // Close the dialog
        });

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(231, 76, 60)); // Red color
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> {
            System.out.println("Form canceled."); // Handle cancel action
            dialog.dispose(); // Close the dialog
        });

        // Add buttons to the form
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        // Add the form panel to the dialog
        dialog.add(formPanel, BorderLayout.CENTER);

        // Show the dialog
        dialog.setVisible(true);
    }
}