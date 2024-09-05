import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {

    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton, dotButton, delButton;
    private JButton historyButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public CalculatorApp() {
        this.setTitle("Calculator");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        dotButton = new JButton(".");
        delButton = new JButton("Del");
        historyButton = new JButton("history");

        functionButtons = new JButton[]{addButton, subButton, mulButton, divButton, eqButton, clrButton, dotButton, delButton, historyButton};

        for (JButton button : functionButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new FunctionButtonListener());
        }

        panel.add(textField);
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(historyButton);
        for(int i=1;i<4;i++){
            panel.add(numberButtons[i]);
        }
        panel.add(divButton);
        for(int i=4;i<7;i++){
            panel.add(numberButtons[i]);
        }
        panel.add(addButton);
        for(int i=7;i<10;i++){
            panel.add(numberButtons[i]);
        }
        panel.add(dotButton);
        panel.add(numberButtons[0]);
        panel.add(subButton);
        panel.add(eqButton);

        for (JButton button : functionButtons) {
            panel.add(button);
        }

        this.add(panel);
    }
    

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            textField.setText(textField.getText() + source.getText());
        }
    }
    historyButton.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActiveEvent e)
            {
                saveDataToDatabase();
            }
        });
        private void saveDataToDatabase() {
            try {
              
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
    
     
                String sql = "INSERT INTO your_table (column_name) VALUES (?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, textField.getText());
    
                    preparedStatement.executeUpdate();
                }
                connection.close();
                JOptionPane.showMessageDialog(this, "Data saved to database!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving data to database");
            }
        }
    private class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            char buttonValue = source.getText().charAt(0);

            switch (buttonValue) {
                case 'C':
                    textField.setText("");
                    break;
                case 'D':
                    String currentText = textField.getText();
                    if (!currentText.isEmpty()) {
                        textField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
                case '=':
                    num2 = Double.parseDouble(textField.getText());
                    result = performOperation();
                    textField.setText(String.valueOf(result));
                    break;
                default:
                    operator = buttonValue;
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                    break;
            }
        }
    }

    private double performOperation() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void clearFields() {
        num1 = 0;
        num2 = 0;
        operator = '\u0000';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
    }
}
