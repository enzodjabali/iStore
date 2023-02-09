package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import org.istore.User;

import static org.istore.GUI.GUI.isNumeric;

public class EditUserRole extends JPanel {
    public EditUserRole() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame editUserRole = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Edit a user role");
        editUserRole.setSize(350, 200);
        editUserRole.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editUserRole.setLocationRelativeTo(null);
        editUserRole.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JTextField newRoleField = new JTextField(20);
        JButton editButton = new JButton("Edit");

        editUserRole.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        editUserRole.add(new JLabel("User ID"), gbc);
        gbc.gridy++;
        editUserRole.add(new JLabel("New role "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        editUserRole.add(new JLabel("<html><h4>Edit a user role</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        editUserRole.add(userIdField, gbc);
        gbc.gridy++;
        editUserRole.add(newRoleField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        editUserRole.add(editButton, gbc);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editButton) {
                    String userId = userIdField.getText();
                    String newRole = newRoleField.getText();

                    gbc.gridy = 4;
                    gbc.gridx = 1;

                    if (isNumeric(userId)) {
                        if (!newRole.isEmpty()) {
                            if (newRole.equals("visitor") || newRole.equals("admin")) {
                                if (new User().updateRole(userId, newRole)) {
                                    editUserRole.dispatchEvent(new WindowEvent(editUserRole, WindowEvent.WINDOW_CLOSING));
                                }
                            } else {
                                gbc.gridy++;
                                JLabel errorMessage = new JLabel("<html><b style='color: red;'>The role must be visitor or admin!</b></html>");
                                editUserRole.add(errorMessage, gbc);
                                editUserRole.setVisible(true);
                            }
                        } else {
                            gbc.gridy++;
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The role can't be empty!</b></html>");
                            editUserRole.add(errorMessage, gbc);
                            editUserRole.setVisible(true);
                        }
                    } else {
                        gbc.gridy++;
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>");
                        editUserRole.add(errorMessage, gbc);
                        editUserRole.setVisible(true);
                    }
                }
            }
        });
    }
}
