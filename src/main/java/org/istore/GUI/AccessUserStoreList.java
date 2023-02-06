package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static org.istore.GUI.GUI.isNumeric;

public class AccessUserStoreList extends JPanel {
    public AccessUserStoreList() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame accessUserStoreListFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Access list of users from a store");
        accessUserStoreListFrame.setSize(350,200);
        accessUserStoreListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        accessUserStoreListFrame.setLocationRelativeTo(null);
        accessUserStoreListFrame.setVisible(true);

        JTextField storeIdField = new JTextField(20);
        JButton storeAccessButton = new JButton("Access");

        accessUserStoreListFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        accessUserStoreListFrame.add(new JLabel("Store ID"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        accessUserStoreListFrame.add(new JLabel("<html><h4>Access list of users from a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        accessUserStoreListFrame.add(storeIdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        accessUserStoreListFrame.add(storeAccessButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;

        storeAccessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == storeAccessButton) {
                    String storeId = storeIdField.getText();

                    if (isNumeric(storeId)) {
                        accessUserStoreListFrame.dispatchEvent(new WindowEvent(accessUserStoreListFrame, WindowEvent.WINDOW_CLOSING));
                        new UserStoreList(storeId);
                    } else {
                        gbc.gridy++;
                        accessUserStoreListFrame.add(new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>"), gbc);
                        accessUserStoreListFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
