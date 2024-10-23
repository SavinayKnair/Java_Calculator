import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp implements ActionListener {
    JFrame frame;
    JTextField display;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, equButton, clrButton, backspaceButton; // Backspace button added
    JPanel panel;
    char operator;
    double num1 = 0, num2 = 0, result = 0;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        frame.setSize(400, 600); // Increased height for additional buttons
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Display Settings
        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        frame.add(display);

        // Operator Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("="); // Equals button
        clrButton = new JButton("C");
        backspaceButton = new JButton("←"); // Backspace button

        // Number Buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
        }

        // Add Colors and Listeners
        addButton.setBackground(Color.LIGHT_GRAY);
        subButton.setBackground(Color.LIGHT_GRAY);
        mulButton.setBackground(Color.LIGHT_GRAY);
        divButton.setBackground(Color.LIGHT_GRAY);
        equButton.setBackground(Color.GREEN);
        clrButton.setBackground(Color.RED);
        backspaceButton.setBackground(Color.YELLOW); // Backspace button color

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equButton.addActionListener(this);
        clrButton.addActionListener(this);
        backspaceButton.addActionListener(this); // Add action listener for backspace

        // Panel for buttons layout
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 400); // Adjusted height to fit all buttons
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for more control
        GridBagConstraints gbc = new GridBagConstraints(); // Create GridBagConstraints

        // Add buttons to panel in the desired order
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontal and vertical
        gbc.weightx = 1; // Weight for horizontal scaling
        gbc.weighty = 1; // Weight for vertical scaling
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Positioning number buttons and operators
        gbc.gridx = 0; gbc.gridy = 0; panel.add(numberButtons[7], gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(numberButtons[8], gbc);
        gbc.gridx = 2; gbc.gridy = 0; panel.add(numberButtons[9], gbc);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(divButton, gbc);      // Division Button

        gbc.gridx = 0; gbc.gridy = 1; panel.add(numberButtons[4], gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(numberButtons[5], gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(numberButtons[6], gbc);
        gbc.gridx = 3; gbc.gridy = 1; panel.add(mulButton, gbc);      // Multiplication Button

        gbc.gridx = 0; gbc.gridy = 2; panel.add(numberButtons[1], gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(numberButtons[2], gbc);
        gbc.gridx = 2; gbc.gridy = 2; panel.add(numberButtons[3], gbc);
        gbc.gridx = 3; gbc.gridy = 2; panel.add(subButton, gbc);      // Subtraction Button

        gbc.gridx = 0; gbc.gridy = 3; panel.add(clrButton, gbc);      // Clear Button
        gbc.gridx = 1; gbc.gridy = 3; panel.add(numberButtons[0], gbc); // Number 0 button
        gbc.gridx = 2; gbc.gridy = 3; panel.add(backspaceButton, gbc); // Backspace Button
        gbc.gridx = 3; gbc.gridy = 3; panel.add(addButton, gbc);      // Addition Button next to Backspace

        // Equals button centered in the last row
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4; // Span 4 columns for equals button
        panel.add(equButton, gbc); // Equals Button spanning the full row

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Action listener method
    public void actionPerformed(ActionEvent e) {
        // Handle number button presses
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
            }
        }

        // Handle operator button presses
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText(display.getText() + " + ");  // Keep the number displayed
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText(display.getText() + " - ");  // Keep the number displayed
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText(display.getText() + " * ");  // Keep the number displayed
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText(display.getText() + " / ");  // Keep the number displayed
        }
        
        // Calculate and show result
        if (e.getSource() == equButton) {
            String[] parts = display.getText().split(" ");  // Split display text
            if (parts.length == 3) {  // Ensure we have a complete operation
                num2 = Double.parseDouble(parts[2]);
                switch (parts[1].charAt(0)) {  // Get the operator
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));  // Display the result
                num1 = result;  // Store result for further calculations
            }
        }
        
        // Clear the display
        if (e.getSource() == clrButton) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // Backspace functionality
        if (e.getSource() == backspaceButton) {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                // Remove the last character
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
