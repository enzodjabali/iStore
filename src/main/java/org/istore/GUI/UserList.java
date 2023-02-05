package org.istore.GUI;

import org.istore.Listing;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.util.ArrayList;

public class UserList extends JPanel {

    public UserList() {
        Dotenv dotenv = Dotenv.configure().load();
        Listing listingQueries = new Listing();

        JFrame userListFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - User list");
        userListFrame.setSize(600,350);
        userListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userListFrame.setLocationRelativeTo(null);
        userListFrame.setVisible(true);

        ArrayList<ArrayList<String>> userList = listingQueries.getUserList();

        String header[] = {"ID", "Email", "Name", "Pseudo", "Whitelisted"};

        String[][] data = new String[userList.size()][2];

        for (int i = 0; i < userList.size(); i++) {
            data[i] = new String[] {
                    userList.get(i).get(0),
                    userList.get(i).get(1),
                    userList.get(i).get(2),
                    userList.get(i).get(3),
                    userList.get(i).get(4)
            };
        }

        JTable table = new JTable(data, header);
        userListFrame.getContentPane().add(new JScrollPane(table));
        userListFrame.setVisible(true);
    }
}
