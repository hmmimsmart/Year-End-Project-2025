import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tracker extends JFrame {

    private JLabel label;
    private JTextArea textArea;
    private JButton button;

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
                String input = textArea.getText();
                System.out.println("User Input: " + input);
                textArea.setText(""); // Clear after input
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tracker::new);
    }
}