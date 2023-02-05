package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.User;
import static org.istore.GUI.GUI.isNumeric;

public class EditUser extends JPanel {
    public EditUser() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame editUserFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Edit a user");
        editUserFrame.setSize(350, 200);
        editUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editUserFrame.setLocationRelativeTo(null);
        editUserFrame.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JTextField pseudoField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);

        JButton editButton = new JButton("Edit");

        editUserFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        editUserFrame.add(new JLabel("User ID"), gbc);
        gbc.gridy++;
        editUserFrame.add(new JLabel("New pseudo"), gbc);
        gbc.gridy++;
        editUserFrame.add(new JLabel("New email"), gbc);
        gbc.gridy++;
        editUserFrame.add(new JLabel("New password "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        editUserFrame.add(new JLabel("<html><h4>Edit a user</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        editUserFrame.add(userIdField, gbc);
        gbc.gridy++;
        editUserFrame.add(pseudoField, gbc);
        gbc.gridy++;
        editUserFrame.add(emailField, gbc);
        gbc.gridy++;
        editUserFrame.add(passwordField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        editUserFrame.add(editButton, gbc);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editButton) {
                    String userId = userIdField.getText();
                    String newPseudo = pseudoField.getText();
                    String newEmail = emailField.getText();
                    String newPassword = emailField.getText();

                    gbc.gridy = 6;
                    gbc.gridx = 1;

                    if (isNumeric(userId)) {
                        if (!newPseudo.isEmpty() && !newEmail.isEmpty() && !newPassword.isEmpty()) {
                            if (new User().userUpdate(userId, newPseudo, newEmail, newPassword)) {
                                editUserFrame.dispatchEvent(new WindowEvent(editUserFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                            editUserFrame.add(errorMessage, gbc);
                            editUserFrame.setVisible(true);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>The user ID needs to be a number!</b></html>");
                        editUserFrame.add(errorMessage, gbc);
                        editUserFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
