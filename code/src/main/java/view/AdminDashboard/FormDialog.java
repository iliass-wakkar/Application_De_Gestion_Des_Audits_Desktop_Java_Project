package view.AdminDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FormDialog<T> extends JDialog {
    private JButton saveButton = new JButton("Save");
    private Map<String, String> formData = new HashMap<>();
    private Map<String, JTextField> fields = new HashMap<>();
    private JButton cancelButton;

    public FormDialog(String title, String[] fieldNames, Object[] data) {
        createForm(title, fieldNames, data);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public Map<String, String> getFormData() {
        collectFormData(); // Ensure data is collected before returning
        return formData;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    private void createForm(String title, String[] fieldNames, Object[] data) {
        this.setTitle(title);
        this.setModal(true);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(236, 240, 241));

        JPanel formPanel = new JPanel(new GridLayout(fieldNames.length + 1, 2, 10, 10));
        formPanel.setBackground(new Color(236, 240, 241));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];

            JLabel label = new JLabel(fieldName + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(new Color(52, 73, 94));
            formPanel.add(label);

            JTextField textField = new JTextField();
            textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            if (data != null && i < data.length && data[i] != null) {
                textField.setText(data[i].toString());
            }

            fields.put(fieldName, textField);
            formPanel.add(textField);
        }

        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(52, 152, 219));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(231, 76, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        this.add(formPanel, BorderLayout.CENTER);
    }

    public void collectFormData() {
        formData.clear();
        for (Map.Entry<String, JTextField> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue().getText();
            formData.put(fieldName, fieldValue);
        }
    }

    public boolean validateForm() {
        for (Map.Entry<String, JTextField> entry : fields.entrySet()) {
            String fieldValue = entry.getValue().getText().trim();
            if (fieldValue.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}