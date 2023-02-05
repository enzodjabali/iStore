package org.istore.GUI;

import org.istore.Listing;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home extends JPanel {
    public Home() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame homeFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - List of stores");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(800, 600);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);

        Listing listingQueries = new Listing();

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu menuStore = new JMenu("Stores");
        JMenu menuUsers = new JMenu("Users");

        mb.add(menuStore);
        mb.add(menuUsers);

        JMenuItem menuAccessStore = new JMenuItem("Access a store");
        JMenuItem menuAddStore = new JMenuItem("Create a store");
        JMenuItem menuEditStore = new JMenuItem("Edit a store");
        JMenuItem menuDeleteStore = new JMenuItem("Delete a store");
        menuStore.add(menuAccessStore);
        menuStore.add(menuAddStore);
        menuStore.add(menuEditStore);
        menuStore.add(menuDeleteStore);

        JMenuItem menuUserList = new JMenuItem("List of users");
        JMenuItem menuAddUser = new JMenuItem("Create a user");
        JMenuItem menuEditUser = new JMenuItem("Edit a user");
        JMenuItem menuDeleteUser = new JMenuItem("Delete a store");
        menuUsers.add(menuUserList);
        menuUsers.add(menuAddUser);
        menuUsers.add(menuEditUser);
        menuUsers.add(menuDeleteUser);

        homeFrame.getContentPane().add(BorderLayout.NORTH, mb);

        // home page here with store listing
        ArrayList<ArrayList<String>> storeList = listingQueries.getStoreList();

        String header[] = {"ID","Name"};

        String[][] data = new String[storeList.size()][2];

        for (int i = 0; i < storeList.size(); i++) {
            data[i] = new String[]{storeList.get(i).get(0), storeList.get(i).get(1)};
        }

        JTable table = new JTable(data, header);
        homeFrame.getContentPane().add(new JScrollPane(table));
        homeFrame.setVisible(true);


        // click on access store via menu
        menuAccessStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuAccessStore) {
                    new AccessStore();
                }
            }
        });

        menuAddStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuAddStore) {
                    new AddStore(homeFrame);
                }
            }
        });

        menuEditStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuEditStore) {
                    new EditStore(homeFrame);
                }
            }
        });

        menuDeleteStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuDeleteStore) {
                    new DeleteStore(homeFrame);
                }
            }
        });

        menuUserList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuUserList) {
                    new UserList();
                }
            }
        });

    }
}