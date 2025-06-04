import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class Tracker extends JFrame {

    private JLabel label;
    private JTextArea textArea;
    private JButton button;

    private static final String FILE_NAME = "budget_log.txt";

    public Tracker() {
        setTitle("Budget Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        label = new JLabel("Enter your budget log:", SwingConstants.CENTER);
        textArea = new JTextArea(5, 30);
        button = new JButton("Submit");

        add(label, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textArea.getText().trim();
                if (!input.isEmpty()) {
                    saveToFile(input);
                    textArea.setText("");
                }
            }
        });

        setVisible(true);
    }

    private void saveToFile(String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tracker::new);
    }
}
