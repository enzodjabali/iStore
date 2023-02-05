package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.User;
import static org.istore.GUI.GUI.isNumeric;

public class WhitelistUser extends JPanel {
    public WhitelistUser() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame whitelistUserFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Whitelist/blacklist a user");
        whitelistUserFrame.setSize(350, 200);
        whitelistUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        whitelistUserFrame.setLocationRelativeTo(null);
        whitelistUserFrame.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JTextField whitelistStateField = new JTextField(20);
        JButton applyWhitelistButton = new JButton("Apply");

        whitelistUserFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        whitelistUserFrame.add(new JLabel("User ID"), gbc);
        gbc.gridy++;
        whitelistUserFrame.add(new JLabel("Whitelist state "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        whitelistUserFrame.add(new JLabel("<html><h4>Whitelist/blacklist a user</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        whitelistUserFrame.add(userIdField, gbc);
        gbc.gridy++;
        whitelistUserFrame.add(whitelistStateField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        whitelistUserFrame.add(applyWhitelistButton, gbc);


        applyWhitelistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == applyWhitelistButton) {
                    String userId = userIdField.getText();
                    String whitelistState = whitelistStateField.getText();

                    gbc.gridy = 5;
                    gbc.gridx = 1;

                    if (isNumeric(userId) && isNumeric(whitelistState)) {
                        if (whitelistState.equals("0") || whitelistState.equals("1")) {
                            if (new User().whitelistUser(whitelistState, userId)) {
                                whitelistUserFrame.dispatchEvent(new WindowEvent(whitelistUserFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>Whitelist's state has to be 1 or 0!</b></html>");
                            whitelistUserFrame.add(errorMessage, gbc);
                            whitelistUserFrame.setVisible(true);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, enter numbers!</b></html>");
                        whitelistUserFrame.add(errorMessage, gbc);
                        whitelistUserFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
