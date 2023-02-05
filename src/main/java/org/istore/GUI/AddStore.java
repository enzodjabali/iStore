package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import org.istore.StoreManager;
import org.istore.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddStore extends JPanel {
    public AddStore(JFrame homeFrame, User myUser) {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame addStoreFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Create a store");
        addStoreFrame.setSize(350,200);
        addStoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStoreFrame.setLocationRelativeTo(null);
        addStoreFrame.setVisible(true);

        JTextField storeNameField = new JTextField(20);
        JButton storeAddButton = new JButton("Create");

        addStoreFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        addStoreFrame.add(new JLabel("Name"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        addStoreFrame.add(new JLabel("<html><h4>Create a store</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addStoreFrame.add(storeNameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addStoreFrame.add(storeAddButton, gbc);

        storeAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == storeAddButton) {
                    if (new StoreManager().createStore(storeNameField.getText())) {
                        addStoreFrame.dispatchEvent(new WindowEvent(addStoreFrame, WindowEvent.WINDOW_CLOSING));
                        homeFrame.dispose();
                        new Home(myUser);
                    }
                }
            }
        });
    }
}
