import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExample extends JFrame {
    private JTextField textField;
    private JButton saveButton;

    public DatabaseExample() {
        // Set up the frame and layout
        setTitle("Database Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create components
        textField = new JTextField(20);
        saveButton = new JButton("Save to Database");

        // Add components to the frame
        add(textField);
        add(saveButton);

        // Add ActionListener to the button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform database operation
                saveDataToDatabase();
       }
        });

        // Set frame properties
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveDataToDatabase() {
        try {
            // Establish database connection (replace with your database URL, username, and password)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            // Prepare SQL statement (replace with your table structure and query)
            String sql = "INSERT INTO your_table (column_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set parameter values
                preparedStatement.setString(1, textField.getText());

                // Execute the query
                preparedStatement.executeUpdate();
            }

            // Close the connection
            connection.close();
            // Optionally, display a success message
            JOptionPane.showMessageDialog(this, "Data saved to database!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any database errors
            JOptionPane.showMessageDialog(this, "Error saving data to database");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatabaseExample();
            }
        });
    }
}