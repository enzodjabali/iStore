package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.StoreManager;
import static org.istore.GUI.GUI.isNumeric;

public class DeleteStore extends JPanel {
    public DeleteStore(JFrame homeFrame) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame deleteStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Delete a store");
        deleteStoreFrame.setSize(350,200);
        deleteStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteStoreFrame.setLocationRelativeTo(null);
        deleteStoreFrame.setVisible(true);

        JTextField storeIdField = new JTextField(20);
        JButton storeDeleteButton = new JButton("Delete");

        deleteStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteStoreFrame.add(new JLabel("Store ID"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        deleteStoreFrame.add(new JLabel("<html><h4>Delete a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        deleteStoreFrame.add(storeIdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        deleteStoreFrame.add(storeDeleteButton, gbc);

        storeDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == storeDeleteButton) {
                    String storeId = storeIdField.getText();

                    if (isNumeric(storeId)) {
                        if (new StoreManager().deleteStore(storeId)) {
                            deleteStoreFrame.dispatchEvent(new WindowEvent(deleteStoreFrame, WindowEvent.WINDOW_CLOSING));
                            homeFrame.dispose();
                            new Home();
                        }
                    } else {
                        gbc.gridy = 5;
                        gbc.gridx = 1;

                        deleteStoreFrame.add(new JLabel("<html><b style='color: red;'>Please, enter a number!</b></html>"), gbc);
                        deleteStoreFrame.setVisible(true);
                    }

                }
            }
        });
    }
}
