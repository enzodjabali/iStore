package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class EditStore extends JPanel {
    public EditStore(JFrame homeFrame) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame editStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Edit a store");
        editStoreFrame.setSize(350, 200);
        editStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editStoreFrame.setLocationRelativeTo(null);
        editStoreFrame.setVisible(true);

        JTextField storeIdField = new JTextField(20);
        JTextField newNameField = new JTextField(20);
        JButton editButton = new JButton("Edit");

        editStoreFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        editStoreFrame.add(new JLabel("Store ID"), gbc);
        gbc.gridy++;
        editStoreFrame.add(new JLabel("New name "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        editStoreFrame.add(new JLabel("<html><h4>Edit a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        editStoreFrame.add(storeIdField, gbc);
        gbc.gridy++;
        editStoreFrame.add(newNameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        editStoreFrame.add(editButton, gbc);


        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editButton) {
                    String storeId = storeIdField.getText();

                    gbc.gridy = 5;
                    gbc.gridx = 1;
                    JLabel errorMessage = new JLabel("");

                    if (isNumeric(storeId)) {
                        if (!newNameField.getText().isEmpty()) {
                            if (new StoreManager().renameStore(newNameField.getText(), storeId)) {
                                editStoreFrame.dispatchEvent(new WindowEvent(editStoreFrame, WindowEvent.WINDOW_CLOSING));
                                homeFrame.dispose();
                                new Home();
                            }
                        } else {
                            errorMessage = new JLabel("<html><b style='color: red;'>The store's name can't be empty!</b></html>");
                        }
                    } else {
                        errorMessage = new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>");
                    }

                    editStoreFrame.add(errorMessage, gbc);
                    editStoreFrame.setVisible(true);
                }
            }
        });
    }
}
