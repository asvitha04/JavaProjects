/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASVITHA
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

// Applet class definition
public class CalculatorApplet extends Applet implements ActionListener {
    TextField display;
    double num1, num2, result;
    String operator;

    // Applet Initialization
    public void init() {
        // Set applet size (increase width and height for a bigger calculator)
        setSize(400, 500);
        
        // Use FlowLayout to center the calculator
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); 

        // Create a main panel to hold everything
        Panel mainPanel = new Panel();
        mainPanel.setLayout(new BorderLayout());

        // Create and set properties of the display
        display = new TextField();
        display.setEditable(false);
        display.setBackground(new Color(0x1A1A1A)); // Dark background for display
        display.setForeground(new Color(128, 0, 128)); // Purple text for display
        display.setFont(new Font("Arial", Font.BOLD, 30));  // Increase font size
        display.setPreferredSize(new Dimension(380, 50));   // Make the display bigger
        mainPanel.add(display, BorderLayout.NORTH);

        // Create a Panel to hold calculator buttons
        Panel buttonsPanel = new Panel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 10, 10));  // Add gaps between buttons

        // Button texts for the calculator
        String buttonLabels[] = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "C", "0", "=", "+", 
        };

        // Loop through the labels and create buttons
        for (String label : buttonLabels) {
            Button btn = new Button(label);
            btn.setFont(new Font("Arial", Font.BOLD, 24));  // Increase button font size
            btn.setPreferredSize(new Dimension(80, 60));    // Set button size
            btn.addActionListener(this); // Add ActionListener to each button

            // Set button colors based on their function
            switch (label) {
                case "C":
                    btn.setBackground(Color.RED); // Clear button in Red
                    btn.setForeground(Color.WHITE);
                    break;
                case "=":
                    btn.setBackground(Color.GREEN); // Equals button in Green
                    btn.setForeground(Color.WHITE);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    btn.setBackground(Color.ORANGE); // Operator buttons in Orange
                    btn.setForeground(Color.WHITE);
                    break;
                default:
                    btn.setBackground(new Color(135, 206, 250)); // Light blue for number buttons
                    btn.setForeground(Color.BLACK);
                    break;
            }
            buttonsPanel.add(btn);
        }

        // Add the button panel to the main panel
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Add the main panel to the applet
        add(mainPanel);
    }

    // Event handling
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 != 0) {
                        result = num1 / num2; 
                    } else {
                        display.setText("Error"); // Handle division by zero
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            num1 = result; // Store result for next operation
        } else {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        }
    }
}

