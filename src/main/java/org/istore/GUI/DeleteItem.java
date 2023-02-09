package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.Item;
import static org.istore.GUI.GUI.isNumeric;

public class DeleteItem extends JPanel {
    public DeleteItem() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame deleteItemFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Delete an item");
        deleteItemFrame.setSize(350,200);
        deleteItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteItemFrame.setLocationRelativeTo(null);
        deleteItemFrame.setVisible(true);

        JTextField itemIdField = new JTextField(20);
        JButton itemDeleteButton = new JButton("Delete");

        deleteItemFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteItemFrame.add(new JLabel("Item ID"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        deleteItemFrame.add(new JLabel("<html><h4>Delete an item</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        deleteItemFrame.add(itemIdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        deleteItemFrame.add(itemDeleteButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;

        itemDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == itemDeleteButton) {
                    String itemId = itemIdField.getText();

                    if (isNumeric(itemId)) {
                        if (new Item().deleteItem(itemId)) {
                            deleteItemFrame.dispatchEvent(new WindowEvent(deleteItemFrame, WindowEvent.WINDOW_CLOSING));
                        }
                    } else {
                        gbc.gridy++;
                        deleteItemFrame.add(new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>"), gbc);
                        deleteItemFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
