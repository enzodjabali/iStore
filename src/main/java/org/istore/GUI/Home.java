package org.istore.GUI;

import org.istore.Listing;

import io.github.cdimascio.dotenv.Dotenv;
import org.istore.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home extends JPanel {
    public Home(User myUser) {
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
        JMenu menuMyAccount = new JMenu("My Account - " + myUser.getPseudo());

        mb.add(menuStore);
        mb.add(menuUsers);
        mb.add(menuMyAccount);

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
        JMenuItem menuWhitelistUser = new JMenuItem("Whitelist/blacklist a user");
        JMenuItem menuEditUser = new JMenuItem("Edit a user");
        JMenuItem menuEditUserRole = new JMenuItem("Edit a user role");
        JMenuItem menuDeleteUser = new JMenuItem("Delete a user");
        menuUsers.add(menuUserList);
        menuUsers.add(menuAddUser);
        menuUsers.add(menuWhitelistUser);
        menuUsers.add(menuEditUser);
        menuUsers.add(menuEditUserRole);
        menuUsers.add(menuDeleteUser);

        JMenuItem menuEditMyAccount = new JMenuItem("Edit my account");
        JMenuItem menuDeleteMyAccount = new JMenuItem("Delete my account");
        menuMyAccount.add(menuEditMyAccount);
        menuMyAccount.add(menuDeleteMyAccount);

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
                    new AddStore(homeFrame, myUser);
                }
            }
        });

        menuEditStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuEditStore) {
                    new EditStore(homeFrame, myUser);
                }
            }
        });

        menuDeleteStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuDeleteStore) {
                    new DeleteStore(homeFrame, myUser);
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

        menuAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuAddUser) {
                    new SignUp();
                }
            }
        });

        menuWhitelistUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuWhitelistUser) {
                    new WhitelistUser();
                }
            }
        });

        menuEditUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuEditUser) {
                    new EditUser();
                }
            }
        });

        menuEditUserRole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuEditUserRole) {
                    new EditUserRole();
                }
            }
        });

        menuDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuDeleteUser) {
                    new DeleteUser();
                }
            }
        });

        menuEditMyAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuEditMyAccount) {
                    new EditMyAccount(homeFrame, myUser);
                }
            }
        });

        menuDeleteMyAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuDeleteMyAccount) {
                    new DeleteMyAccount(myUser);
                }
            }
        });

    }
}