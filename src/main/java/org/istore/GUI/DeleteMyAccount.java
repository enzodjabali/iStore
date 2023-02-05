package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.istore.User;

public class DeleteMyAccount extends JPanel {
    public DeleteMyAccount(User myUser) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame deleteAccountFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Delete my account");
        deleteAccountFrame.setSize(350,150);
        deleteAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteAccountFrame.setLocationRelativeTo(null);
        deleteAccountFrame.setVisible(true);

        JButton deleteAccountButton = new JButton("Delete");

        deleteAccountFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 1;
        deleteAccountFrame.add(new JLabel("<html><h4>Are you sure you want to delete your account?</h4></html>"), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        deleteAccountFrame.add(deleteAccountButton, gbc);

        deleteAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == deleteAccountButton) {
                    if (myUser.deleteUser((myUser.getId()))) {
                        System.exit(0);
                    }
                }
            }
        });
    }
}
