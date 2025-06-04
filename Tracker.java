import javax.swing.*;
import java.awt.*;

public class Tracker extends JFrame {

    private JLabel label;
    private JTextArea textArea;
    private JButton button;

    public Tracker() {
        setTitle("Tracker App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        label = new JLabel("Tracker", SwingConstants.CENTER);
        textArea = new JTextArea(5, 30);
        button = new JButton("Submit");

        add(label, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tracker::new);
        System.out.println("i love men");
    }
}
