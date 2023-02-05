package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.User;

public class EditMyAccount extends JPanel {
    public EditMyAccount(JFrame homeFrame, User myUser) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame editMyAccountFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Edit my account");
        editMyAccountFrame.setSize(350, 200);
        editMyAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editMyAccountFrame.setLocationRelativeTo(null);
        editMyAccountFrame.setVisible(true);

        JTextField pseudoField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);

        JButton editButton = new JButton("Edit");

        editMyAccountFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        editMyAccountFrame.add(new JLabel("New pseudo"), gbc);
        gbc.gridy++;
        editMyAccountFrame.add(new JLabel("New email"), gbc);
        gbc.gridy++;
        editMyAccountFrame.add(new JLabel("New password "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        editMyAccountFrame.add(new JLabel("<html><h4>Edit my account: " + myUser.getPseudo() + "</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        editMyAccountFrame.add(pseudoField, gbc);
        gbc.gridy++;
        editMyAccountFrame.add(emailField, gbc);
        gbc.gridy++;
        editMyAccountFrame.add(passwordField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        editMyAccountFrame.add(editButton, gbc);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editButton) {
                    String newPseudo = pseudoField.getText();
                    String newEmail = emailField.getText();
                    String newPassword = emailField.getText();

                    gbc.gridy = 6;
                    gbc.gridx = 1;

                    if (!newPseudo.isEmpty() && !newEmail.isEmpty() && !newPassword.isEmpty()) {
                        if (myUser.userUpdate(myUser.getId(), newPseudo, newEmail, newPassword)) {
                            editMyAccountFrame.dispatchEvent(new WindowEvent(editMyAccountFrame, WindowEvent.WINDOW_CLOSING));
                            homeFrame.dispose();
                            new Home(myUser);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        editMyAccountFrame.add(errorMessage, gbc);
                        editMyAccountFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
