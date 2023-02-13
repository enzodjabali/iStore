package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import org.istore.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import static java.lang.Integer.parseInt;
import static org.istore.GUI.GUI.isNumeric;

public class AccessStore extends JPanel {
    public AccessStore(String userId) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame accessStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Access a store");
        accessStoreFrame.setSize(350,200);
        accessStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        accessStoreFrame.setLocationRelativeTo(null);
        accessStoreFrame.setVisible(true);

        JTextField storeIdField = new JTextField(20);
        JButton storeAccessButton = new JButton("Access");

        accessStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        accessStoreFrame.add(new JLabel("Store ID"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        accessStoreFrame.add(new JLabel("<html><h4>Access a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        accessStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        accessStoreFrame.add(storeAccessButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;

        storeAccessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == storeAccessButton) {
                    String storeId = storeIdField.getText();

                    if (isNumeric(storeId)) {
                        int storeIdInt = parseInt(storeId);
                        accessStoreFrame.dispatchEvent(new WindowEvent(accessStoreFrame, WindowEvent.WINDOW_CLOSING));

                        if (User.doesStoreExist(storeId)){
                            if(User.hasUserAccessToStore(storeId, userId)){
                                new Store(storeIdInt, userId, false);
                                //regarder si idAdmin, remttre la fonction en statique
                            } else if (User.isAdmin(userId)) {
                                new Store(storeIdInt, userId, true);
                            } else{
                                gbc.gridy++;
                                accessStoreFrame.add(new JLabel("<html><b style='color: red;'>You have no rights on this store.</b></html>"), gbc);
                                accessStoreFrame.setVisible(true);
                            }
                        } else{
                            gbc.gridy++;
                            accessStoreFrame.add(new JLabel("<html><b style='color: red;'>This store does not exist</b></html>"), gbc);
                            accessStoreFrame.setVisible(true);
                        }
                    } else {
                        gbc.gridy++;
                        accessStoreFrame.add(new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>"), gbc);
                        accessStoreFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
