package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class AddUserToStore extends JPanel {
    public AddUserToStore() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame addUserToStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Add a user to a store");
        addUserToStoreFrame.setSize(350,200);
        addUserToStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addUserToStoreFrame.setLocationRelativeTo(null);
        addUserToStoreFrame.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JTextField storeIdField = new JTextField(20);
        JButton userAddButton = new JButton("Add");

        addUserToStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        addUserToStoreFrame.add(new JLabel("User ID "), gbc);
        gbc.gridy++;
        addUserToStoreFrame.add(new JLabel("Store ID "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        addUserToStoreFrame.add(new JLabel("<html><h4>Add a user to a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addUserToStoreFrame.add(userIdField, gbc);
        gbc.gridy++;
        addUserToStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addUserToStoreFrame.add(userAddButton, gbc);

        userAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == userAddButton) {
                    if (!userIdField.getText().isEmpty() && !storeIdField.getText().isEmpty()) {
                        if (isNumeric(userIdField.getText()) && isNumeric(storeIdField.getText())) {
                            if (new StoreManager().addUserToStore(userIdField.getText(), storeIdField.getText())) {
                                addUserToStoreFrame.dispatchEvent(new WindowEvent(addUserToStoreFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            gbc.gridy++;
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The fields need to be numbers!</b></html>");
                            addUserToStoreFrame.add(errorMessage, gbc);
                            addUserToStoreFrame.setVisible(true);
                        }
                    } else {
                        gbc.gridy++;
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        addUserToStoreFrame.add(errorMessage, gbc);
                        addUserToStoreFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
