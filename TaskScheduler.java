package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskScheduler extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<String> priorityBox;
    private JComboBox<String> statusBox;
    private JFormattedTextField dueDateField;
    private DefaultTableModel tableModel;

    public TaskScheduler() {
        setTitle("Task Scheduler");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue background

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(224, 255, 255)); // LightCyan background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        titleField = new JTextField(12);
        inputPanel.add(titleField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(2, 12);
        inputPanel.add(new JScrollPane(descriptionArea), gbc);

        // Due Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Due Date:"), gbc);
        gbc.gridx = 1;
        dueDateField = new JFormattedTextField();
        dueDateField.setColumns(12);
        inputPanel.add(dueDateField, gbc);

        // Priority
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Priority:"), gbc);
        gbc.gridx = 1;
        String[] priorities = { "High", "Medium", "Low" };
        priorityBox = new JComboBox<>(priorities);
        inputPanel.add(priorityBox, gbc);

        // Status
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        String[] statuses = { "Pending", "Completed" };
        statusBox = new JComboBox<>(statuses);
        inputPanel.add(statusBox, gbc);

        // Add Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Task");
        addButton.setBackground(new Color(135, 206, 250)); // LightSkyBlue
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);
        inputPanel.add(addButton, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Table setup
        String[] columns = { "Title", "Description", "Due Date", "Priority", "Status" };
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(250, 150));
        table.setFillsViewportHeight(true);

        // Table header color
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180)); // SteelBlue
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        // Center table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            String dueDate = dueDateField.getText();
            String priority = (String) priorityBox.getSelectedItem();
            String status = (String) statusBox.getSelectedItem();

            tableModel.addRow(new Object[] { title, description, dueDate, priority, status });
            clearFields();
        });
    }

    private void clearFields() {
        titleField.setText("");
        descriptionArea.setText("");
        dueDateField.setText("");
        priorityBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaskScheduler().setVisible(true);
        });
    }
}
