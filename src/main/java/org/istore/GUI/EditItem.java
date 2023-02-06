package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.Item;
import static org.istore.GUI.GUI.isNumeric;

public class EditItem extends JPanel {
    public EditItem() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame editItemFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Edit an item");
        editItemFrame.setSize(350,200);
        editItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editItemFrame.setLocationRelativeTo(null);
        editItemFrame.setVisible(true);

        JTextField itemIdField = new JTextField(20);
        JTextField itemNameField = new JTextField(20);
        JTextField itemPriceField = new JTextField(20);
        JButton itemAddButton = new JButton("Edit");

        editItemFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        editItemFrame.add(new JLabel("Item ID "), gbc);
        gbc.gridy++;
        editItemFrame.add(new JLabel("New name "), gbc);
        gbc.gridy++;
        editItemFrame.add(new JLabel("New price "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        editItemFrame.add(new JLabel("<html><h4>Edit an item</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        editItemFrame.add(itemIdField, gbc);
        gbc.gridy++;
        editItemFrame.add(itemNameField, gbc);
        gbc.gridy++;
        editItemFrame.add(itemPriceField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        editItemFrame.add(itemAddButton, gbc);

        itemAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == itemAddButton) {
                    gbc.gridy++;

                    if (isNumeric(itemIdField.getText())) {
                        if (!itemNameField.getText().isEmpty() && !itemPriceField.getText().isEmpty()) {
                            if (isNumeric(itemPriceField.getText())) {
                                if (new Item().updateItem(itemIdField.getText(), itemNameField.getText(), itemPriceField.getText())) {
                                    editItemFrame.dispatchEvent(new WindowEvent(editItemFrame, WindowEvent.WINDOW_CLOSING));
                                }
                            } else {
                                JLabel errorMessage = new JLabel("<html><b style='color: red;'>The price needs to be a number!</b></html>");
                                editItemFrame.add(errorMessage, gbc);
                                editItemFrame.setVisible(true);
                            }
                        } else {
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                            editItemFrame.add(errorMessage, gbc);
                            editItemFrame.setVisible(true);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>The item ID needs to be a number!</b></html>");
                        editItemFrame.add(errorMessage, gbc);
                        editItemFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
