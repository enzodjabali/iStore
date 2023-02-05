package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.User;

import static org.istore.GUI.GUI.isNumeric;

public class DeleteUser extends JPanel {
    public DeleteUser() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame deleteUserFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Delete a user");
        deleteUserFrame.setSize(350,200);
        deleteUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteUserFrame.setLocationRelativeTo(null);
        deleteUserFrame.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JButton userDeleteButton = new JButton("Delete");

        deleteUserFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteUserFrame.add(new JLabel("User ID"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        deleteUserFrame.add(new JLabel("<html><h4>Delete a user</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        deleteUserFrame.add(userIdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        deleteUserFrame.add(userDeleteButton, gbc);

        userDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == userDeleteButton) {
                    String userId = userIdField.getText();

                    if (isNumeric(userId)) {
                        if (new User().deleteUser(userId)) {
                            deleteUserFrame.dispatchEvent(new WindowEvent(deleteUserFrame, WindowEvent.WINDOW_CLOSING));
                        }
                    } else {
                        gbc.gridy = 5;
                        gbc.gridx = 1;

                        deleteUserFrame.add(new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>"), gbc);
                        deleteUserFrame.setVisible(true);
                    }

                }
            }
        });
    }
}
