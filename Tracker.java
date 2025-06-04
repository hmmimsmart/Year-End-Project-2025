import javax.swing.;
import java.awt.;
import java.awt.event.;
import java.io.;
import java.nio.file.*;

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

        label = new JLabel("Enter your budget log:", SwingConstants.CENTER);
        textArea = new JTextArea(5, 30);
        submitButton = new JButton("Submit");
        viewLogButton = new JButton("View Log");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(viewLogButton);

        add(label, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            String input = textArea.getText().trim();
            if (!input.isEmpty()) {
                saveToFile(input);
                textArea.setText("");
            }
        });

        viewLogButton.addActionListener(e -> {
            String contents = readFromFile();
            JOptionPane.showMessageDialog(this, contents.isEmpty() ? "No logs yet." : contents, "Budget Log", JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
    }

    private void saveToFile(String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(data);
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
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tracker::new);
    }
}
