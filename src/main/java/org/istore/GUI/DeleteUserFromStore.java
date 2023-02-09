package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class DeleteUserFromStore extends JPanel {
    public DeleteUserFromStore() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame removeUserFromStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Remove a user from a store");
        removeUserFromStoreFrame.setSize(350,200);
        removeUserFromStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeUserFromStoreFrame.setLocationRelativeTo(null);
        removeUserFromStoreFrame.setVisible(true);

        JTextField userIdField = new JTextField(20);
        JTextField storeIdField = new JTextField(20);
        JButton userRemoveButton = new JButton("Remove");

        removeUserFromStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        removeUserFromStoreFrame.add(new JLabel("User ID "), gbc);
        gbc.gridy++;
        removeUserFromStoreFrame.add(new JLabel("Store ID "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        removeUserFromStoreFrame.add(new JLabel("<html><h4>Remove a user from a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        removeUserFromStoreFrame.add(userIdField, gbc);
        gbc.gridy++;
        removeUserFromStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        removeUserFromStoreFrame.add(userRemoveButton, gbc);

        userRemoveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == userRemoveButton) {
                    if (!userIdField.getText().isEmpty() && !storeIdField.getText().isEmpty()) {
                        if (isNumeric(userIdField.getText()) && isNumeric(storeIdField.getText())) {
                            if (new StoreManager().deleteUserFromStore(userIdField.getText(), storeIdField.getText())) {
                                removeUserFromStoreFrame.dispatchEvent(new WindowEvent(removeUserFromStoreFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            gbc.gridy++;
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The fields need to be numbers!</b></html>");
                            removeUserFromStoreFrame.add(errorMessage, gbc);
                            removeUserFromStoreFrame.setVisible(true);
                        }
                    } else {
                        gbc.gridy++;
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        removeUserFromStoreFrame.add(errorMessage, gbc);
                        removeUserFromStoreFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
