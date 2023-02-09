package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class DeleteItemFromStore extends JPanel {
    public DeleteItemFromStore() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame deleteItemFromStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Remove an item from a store");
        deleteItemFromStoreFrame.setSize(350,200);
        deleteItemFromStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteItemFromStoreFrame.setLocationRelativeTo(null);
        deleteItemFromStoreFrame.setVisible(true);

        JTextField itemIdField = new JTextField(20);
        JTextField storeIdField = new JTextField(20);
        JButton itemDeleteFromStoreButton = new JButton("Remove");

        deleteItemFromStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteItemFromStoreFrame.add(new JLabel("Item ID "), gbc);
        gbc.gridy++;
        deleteItemFromStoreFrame.add(new JLabel("Store ID "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        deleteItemFromStoreFrame.add(new JLabel("<html><h4>Remove an item from a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        deleteItemFromStoreFrame.add(itemIdField, gbc);
        gbc.gridy++;
        deleteItemFromStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        deleteItemFromStoreFrame.add(itemDeleteFromStoreButton, gbc);

        itemDeleteFromStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == itemDeleteFromStoreButton) {
                    if (!itemIdField.getText().isEmpty() && !storeIdField.getText().isEmpty()) {
                        if (isNumeric(itemIdField.getText()) && isNumeric(storeIdField.getText())) {
                            if (new StoreManager().deleteItemFromStore(itemIdField.getText(), storeIdField.getText())) {
                                deleteItemFromStoreFrame.dispatchEvent(new WindowEvent(deleteItemFromStoreFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            gbc.gridy++;
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The fields need to be numbers!</b></html>");
                            deleteItemFromStoreFrame.add(errorMessage, gbc);
                            deleteItemFromStoreFrame.setVisible(true);
                        }
                    } else {
                        gbc.gridy++;
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        deleteItemFromStoreFrame.add(errorMessage, gbc);
                        deleteItemFromStoreFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
