import javax.swing.*;

public class SwingExample {
    public static void main(String[] args) {
        // Creating JFrame
        JFrame frame = new JFrame("Swing Example");

        // Creating JButton
        JButton button = new JButton("Click me");

        // Adding action listener to the button
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hello, Swing!"));

        // Adding button to the frame
        frame.getContentPane().add(button);

        // Setting default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting the size of the frame
        frame.setSize(300, 200);

        // Making the frame visible
        frame.setVisible(true);
    }
}
