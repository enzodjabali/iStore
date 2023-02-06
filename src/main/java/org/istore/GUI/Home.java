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

        ImageIcon addIcon = new ImageIcon("assets/icons/add.png");
        ImageIcon deleteIcon = new ImageIcon("assets/icons/delete.png");
        ImageIcon editIcon = new ImageIcon("assets/icons/edit.png");
        ImageIcon listIcon = new ImageIcon("assets/icons/list.png");
        ImageIcon accessIcon = new ImageIcon("assets/icons/access.png");

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();

        JMenu menuStore = new JMenu("Stores");
        mb.add(menuStore);

        JMenuItem menuAccessStore = new JMenuItem("Access a store");
        menuStore.add(menuAccessStore);
        menuAccessStore.setIcon(accessIcon);

        if (myUser.isAdmin(myUser.getId())) {
            JMenuItem menuAddStore = new JMenuItem("Create a store");
            JMenuItem menuEditStore = new JMenuItem("Edit a store");
            JMenuItem menuDeleteStore = new JMenuItem("Delete a store");
            menuStore.add(menuAddStore);
            menuAddStore.setIcon(addIcon);
            menuStore.add(menuEditStore);
            menuEditStore.setIcon(editIcon);
            menuStore.add(menuDeleteStore);
            menuDeleteStore.setIcon(deleteIcon);

            menuAddStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuAddStore) {
                        new AddStore(homeFrame, myUser);
                    }
                }
            });

            menuEditStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuEditStore) {
                        new EditStore(homeFrame, myUser);
                    }
                }
            });

            menuDeleteStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuDeleteStore) {
                        new DeleteStore(homeFrame, myUser);
                    }
                }
            });
        }

        if (myUser.isAdmin(myUser.getId())) {
            JMenu menuStoreAccess = new JMenu("Stores Access");
            mb.add(menuStoreAccess);

            JMenuItem menuAddUserToStore = new JMenuItem("Add a user to a store");
            JMenuItem menuRemoveUserFromStore = new JMenuItem("Remove a user from a store");
            menuStoreAccess.add(menuAddUserToStore);
            menuAddUserToStore.setIcon(addIcon);
            menuStoreAccess.add(menuRemoveUserFromStore);
            menuRemoveUserFromStore.setIcon(deleteIcon);

            menuAddUserToStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuAddUserToStore) {
                        new AddUserToStore();
                    }
                }
            });

            menuRemoveUserFromStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuRemoveUserFromStore) {
                        new DeleteUserFromStore();
                    }
                }
            });
        }

        JMenu menuUsers = new JMenu("Users");
        mb.add(menuUsers);

        JMenuItem menuUserList = new JMenuItem("List of users");
        menuUsers.add(menuUserList);
        menuUserList.setIcon(listIcon);

        if (myUser.isAdmin(myUser.getId())) {
            JMenuItem menuAddUser = new JMenuItem("Create a user");
            JMenuItem menuWhitelistUser = new JMenuItem("Whitelist/blacklist a user");
            JMenuItem menuEditUser = new JMenuItem("Edit a user");
            JMenuItem menuEditUserRole = new JMenuItem("Edit a user role");
            JMenuItem menuDeleteUser = new JMenuItem("Delete a user");
            menuUsers.add(menuAddUser);
            menuAddUser.setIcon(addIcon);
            menuUsers.add(menuWhitelistUser);
            menuWhitelistUser.setIcon(editIcon);
            menuUsers.add(menuEditUser);
            menuEditUser.setIcon(editIcon);
            menuUsers.add(menuEditUserRole);
            menuEditUserRole.setIcon(editIcon);
            menuUsers.add(menuDeleteUser);
            menuDeleteUser.setIcon(deleteIcon);

            menuAddUser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuAddUser) {
                        new SignUp();
                    }
                }
            });

            menuWhitelistUser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuWhitelistUser) {
                        new WhitelistUser();
                    }
                }
            });

            menuEditUser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuEditUser) {
                        new EditUser();
                    }
                }
            });

            menuEditUserRole.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuEditUserRole) {
                        new EditUserRole();
                    }
                }
            });

            menuDeleteUser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuDeleteUser) {
                        new DeleteUser();
                    }
                }
            });
        }

        if (myUser.isAdmin(myUser.getId())) {
            JMenu menuInventories = new JMenu("Inventories");
            mb.add(menuInventories);

            JMenuItem menuItemList = new JMenuItem("List of items");
            JMenuItem menuAddItem = new JMenuItem("Create an item");
            JMenuItem menuDeleteItem = new JMenuItem("Delete an item");
            JMenuItem menuEditItem = new JMenuItem("Edit an item");
            JMenuItem menuAddItemToStore = new JMenuItem("Add an item to a store");
            JMenuItem menuRemoveItemFromStore = new JMenuItem("Remove an item from a store");
            menuInventories.add(menuItemList);
            menuItemList.setIcon(listIcon);
            menuInventories.add(menuAddItem);
            menuAddItem.setIcon(addIcon);
            menuInventories.add(menuDeleteItem);
            menuDeleteItem.setIcon(deleteIcon);
            menuInventories.add(menuEditItem);
            menuEditItem.setIcon(editIcon);
            menuInventories.add(menuAddItemToStore);
            menuAddItemToStore.setIcon(addIcon);
            menuInventories.add(menuRemoveItemFromStore);
            menuRemoveItemFromStore.setIcon(deleteIcon);

            menuItemList.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuItemList) {
                        new ItemList();
                    }
                }
            });

            menuAddItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuAddItem) {
                        new AddItem();
                    }
                }
            });

            menuDeleteItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuDeleteItem) {
                        new DeleteItem();
                    }
                }
            });

            menuEditItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuEditItem) {
                        new EditItem();
                    }
                }
            });

            menuAddItemToStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuAddItemToStore) {
                        new AddItemToStore();
                    }
                }
            });

            menuRemoveItemFromStore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == menuRemoveItemFromStore) {
                        new DeleteItemFromStore();
                    }
                }
            });
        }

        JMenu menuMyAccount = new JMenu("My Account - " + myUser.getPseudo());
        mb.add(menuMyAccount);

        JMenuItem menuEditMyAccount = new JMenuItem("Edit my account");
        JMenuItem menuDeleteMyAccount = new JMenuItem("Delete my account");
        menuMyAccount.add(menuEditMyAccount);
        menuEditMyAccount.setIcon(editIcon);
        menuMyAccount.add(menuDeleteMyAccount);
        menuDeleteMyAccount.setIcon(deleteIcon);

        homeFrame.getContentPane().add(BorderLayout.NORTH, mb);

        // home page here with store listing
        String idUser = myUser.getId();
        ArrayList<ArrayList<String>> storeList = listingQueries.getStoreList(idUser, myUser.isAdmin(idUser));

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

        menuUserList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuUserList) {
                    new UserList();
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