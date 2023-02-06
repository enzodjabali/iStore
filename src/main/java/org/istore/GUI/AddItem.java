package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.Item;
import static org.istore.GUI.GUI.isNumeric;

public class AddItem extends JPanel {
    public AddItem() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame addItemFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Create an item");
        addItemFrame.setSize(350,200);
        addItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemFrame.setLocationRelativeTo(null);
        addItemFrame.setVisible(true);

        JTextField itemNameField = new JTextField(20);
        JTextField itemPriceField = new JTextField(20);
        JButton itemAddButton = new JButton("Create");

        addItemFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        addItemFrame.add(new JLabel("Name "), gbc);
        gbc.gridy++;
        addItemFrame.add(new JLabel("Price "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        addItemFrame.add(new JLabel("<html><h4>Create an item</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addItemFrame.add(itemNameField, gbc);
        gbc.gridy++;
        addItemFrame.add(itemPriceField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addItemFrame.add(itemAddButton, gbc);

        itemAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == itemAddButton) {
                    gbc.gridy++;

                    if (!itemNameField.getText().isEmpty() && !itemPriceField.getText().isEmpty()) {
                        if (isNumeric(itemPriceField.getText())) {
                            if (new Item().createItem(itemNameField.getText(), itemPriceField.getText())) {
                                addItemFrame.dispatchEvent(new WindowEvent(addItemFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        } else {
                            JLabel errorMessage = new JLabel("<html><b style='color: red;'>The price needs to be a number!</b></html>");
                            addItemFrame.add(errorMessage, gbc);
                            addItemFrame.setVisible(true);
                        }
                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        addItemFrame.add(errorMessage, gbc);
                        addItemFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
