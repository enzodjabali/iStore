package org.istore.GUI;

import org.istore.Listing;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.util.ArrayList;

public class Store extends JPanel {

    public Store(int storeId, String userId) {
        Dotenv dotenv = Dotenv.configure().load();
        Listing listingQueries = new Listing();

        JFrame storeFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Store content");
        storeFrame.setSize(400,500);
        storeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        storeFrame.setLocationRelativeTo(null);
        storeFrame.setVisible(true);

        ArrayList<ArrayList<String>> storeList = listingQueries.getStoreItems(storeId, userId);

        String header[] = {"ID", "Name", "Price", "Store"};

        String[][] data = new String[storeList.size()][2];
        for (int i = 0; i < storeList.size(); i++) {
            data[i] = new String[] {
                    storeList.get(i).get(0),
                    storeList.get(i).get(1),
                    storeList.get(i).get(2),
                    storeList.get(i).get(3)
            };
        }
        if(storeList.size() == 0){
            JLabel object = new JLabel("Ce store n'existe pas OU vous n'avez pas accès à celui-ci");
            storeFrame.getContentPane().add(new JScrollPane(object));
            storeFrame.setVisible(true);
        } else {
            JTable object = new JTable(data, header);
            storeFrame.getContentPane().add(new JScrollPane(object));
            storeFrame.setVisible(true);
        }
    }
}
