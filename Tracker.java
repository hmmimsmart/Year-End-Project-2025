import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tracker extends JFrame {

    private JLabel label;
    private JTextArea textArea;
    private JButton submitButton;
    private JButton viewLogButton;

    private static final String FILE_NAME = "budget_log.txt";

    public Tracker() {
        setTitle("Budget Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLayout(new BorderLayout());

        // Label
        label = new JLabel("Enter your budget log:", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        // Text Area
        textArea = new JTextArea(5, 30);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Buttons
        submitButton = new JButton("Submit");
        viewLogButton = new JButton("View Log");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(viewLogButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Submit action
        submitButton.addActionListener((ActionEvent e) -> {
            String input = textArea.getText().trim();
            if (!input.isEmpty()) {
                saveToFile(input);
                textArea.setText("");
            } else {
                showError("Input cannot be empty.");
            }
        });

        // View Log action
        viewLogButton.addActionListener((ActionEvent e) -> {
            String contents = readFromFile();
            JOptionPane.showMessageDialog(this,
                    contents.isEmpty() ? "No logs found." : contents,
                    "Budget Log",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
    }

    private void saveToFile(String data) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String entry = "[" + timestamp + "] " + data;

        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            showError("Error writing to file.");
        }
    }

    private String readFromFile() {
        try {
            return Files.readString(Paths.get(FILE_NAME));
        } catch (IOException e) {
            return "Error reading file.";
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tracker::new);
    }
}
