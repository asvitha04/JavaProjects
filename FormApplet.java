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

public class FormApplet extends Applet implements ActionListener {
    // Form Components
    CheckboxGroup genderGroup;
    Checkbox male, female;
    TextField nameField;
    TextArea addressField;
    Button submitButton;
    Choice countryChoice;
    List hobbyList;
    Checkbox javaCheckBox, pythonCheckBox, cCheckBox;

    // Output Panel
    Panel outputPanel;

    @Override
    public void init() {
        setLayout(new BorderLayout());

        // Create input form panel
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));

        // Name input
        formPanel.add(new Label("Name:"));
        nameField = new TextField(20);
        formPanel.add(nameField);

        // Gender input (Radio Buttons)
        formPanel.add(new Label("Gender:"));
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, false);
        female = new Checkbox("Female", genderGroup, false);
        Panel genderPanel = new Panel();
        genderPanel.add(male);
        genderPanel.add(female);
        formPanel.add(genderPanel);

        // Country input (Dropdown)
        formPanel.add(new Label("Country:"));
        countryChoice = new Choice();
        countryChoice.add("India");
        countryChoice.add("USA");
        countryChoice.add("UK");
        countryChoice.add("Canada");
        formPanel.add(countryChoice);

        // Hobby input (List)
        formPanel.add(new Label("Hobbies:"));
        hobbyList = new List();
        hobbyList.add("Reading");
        hobbyList.add("Traveling");
        hobbyList.add("Cooking");
        hobbyList.add("Gaming");
        formPanel.add(hobbyList);

        // Skills input (Checkboxes)
        formPanel.add(new Label("Skills:"));
        javaCheckBox = new Checkbox("Java");
        pythonCheckBox = new Checkbox("Python");
        cCheckBox = new Checkbox("C");
        Panel skillsPanel = new Panel();
        skillsPanel.add(javaCheckBox);
        skillsPanel.add(pythonCheckBox);
        skillsPanel.add(cCheckBox);
        formPanel.add(skillsPanel);

        // Address input (Text Area)
        formPanel.add(new Label("Address:"));
        addressField = new TextArea(3, 20);
        formPanel.add(addressField);

        // Submit button
        submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        formPanel.add(submitButton);

        add(formPanel, BorderLayout.NORTH);

        // Output Panel for displaying results
        outputPanel = new Panel();
        outputPanel.setLayout(new GridLayout(0, 2));
        add(outputPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Clear the output panel before displaying new data
        outputPanel.removeAll();

        // Get and display the entered values
        outputPanel.add(new Label("Name:"));
        outputPanel.add(new Label(nameField.getText()));

        // Gender
        outputPanel.add(new Label("Gender:"));
        outputPanel.add(new Label(genderGroup.getSelectedCheckbox() != null ? genderGroup.getSelectedCheckbox().getLabel() : "Not selected"));

        // Country
        outputPanel.add(new Label("Country:"));
        outputPanel.add(new Label(countryChoice.getSelectedItem()));

        // Hobbies (selected items in List)
        outputPanel.add(new Label("Hobbies:"));
        String[] selectedHobbies = hobbyList.getSelectedItems();
        if (selectedHobbies.length > 0) {
            StringBuilder hobbies = new StringBuilder();
            for (String hobby : selectedHobbies) {
                hobbies.append(hobby).append(", ");
            }
            outputPanel.add(new Label(hobbies.toString().replaceAll(", $", "")));
        } else {
            outputPanel.add(new Label("None selected"));
        }

        // Skills (checkboxes)
        outputPanel.add(new Label("Skills:"));
        StringBuilder skills = new StringBuilder();
        if (javaCheckBox.getState()) skills.append("Java ");
        if (pythonCheckBox.getState()) skills.append("Python ");
        if (cCheckBox.getState()) skills.append("C ");
        outputPanel.add(new Label(skills.length() > 0 ? skills.toString() : "None selected"));

        // Address
        outputPanel.add(new Label("Address:"));
        outputPanel.add(new Label(addressField.getText()));

       
        revalidate();
        repaint();
    }
}

