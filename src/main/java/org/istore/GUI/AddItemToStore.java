package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class AddItemToStore extends JPanel {
    public AddItemToStore() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame addItemToStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Add an item to a store");
        addItemToStoreFrame.setSize(350,200);
        addItemToStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemToStoreFrame.setLocationRelativeTo(null);
        addItemToStoreFrame.setVisible(true);

        JTextField itemIdField = new JTextField(20);
        JTextField storeIdField = new JTextField(20);
        JButton itemAddButton = new JButton("Add");

        addItemToStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        addItemToStoreFrame.add(new JLabel("Item ID "), gbc);
        gbc.gridy++;
        addItemToStoreFrame.add(new JLabel("Store ID "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        addItemToStoreFrame.add(new JLabel("<html><h4>Add an item to a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addItemToStoreFrame.add(itemIdField, gbc);
        gbc.gridy++;
        addItemToStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addItemToStoreFrame.add(itemAddButton, gbc);

        itemAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == itemAddButton) {
                    gbc.gridy++;

                    if (!itemIdField.getText().isEmpty() && !storeIdField.getText().isEmpty()) {
                        if (isNumeric(itemIdField.getText()) && isNumeric(storeIdField.getText())) {
                            if (new StoreManager().addItemToStore(itemIdField.getText(), storeIdField.getText())) {
                                addItemToStoreFrame.dispatchEvent(new WindowEvent(addItemToStoreFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The fields need to be numbers!</b></html>");
                            addItemToStoreFrame.add(errorMessage, gbc);
                            addItemToStoreFrame.setVisible(true);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        addItemToStoreFrame.add(errorMessage, gbc);
                        addItemToStoreFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
