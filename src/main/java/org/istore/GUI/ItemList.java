package org.istore.GUI;

import org.istore.Listing;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.util.ArrayList;

public class ItemList extends JPanel {

    public ItemList() {
        Dotenv dotenv = Dotenv.configure().load();
        Listing listingQueries = new Listing();

        JFrame userListFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Item list");
        userListFrame.setSize(350,600);
        userListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userListFrame.setLocationRelativeTo(null);
        userListFrame.setVisible(true);

        ArrayList<ArrayList<String>> userList = listingQueries.getItemList();
        String header[] = {"ID", "Name", "Price"};
        String[][] data = new String[userList.size()][2];

        for (int i = 0; i < userList.size(); i++) {
            data[i] = new String[] {
                    userList.get(i).get(0),
                    userList.get(i).get(1),
                    userList.get(i).get(2)
            };
        }

        JTable table = new JTable(data, header);
        userListFrame.getContentPane().add(new JScrollPane(table));
        userListFrame.setVisible(true);
    }
}
