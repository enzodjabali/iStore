package org.istore;

import java.sql.Connection;
import java.sql.Statement;

public class Item extends Admin {
    DBManager database = new DBManager();
    Connection connect = database.dbconnect();

    public boolean createItem(String itemName, String itemPrice) {
        try {
            Statement statement = connect.createStatement();
            String sql = "INSERT INTO items (name, price) VALUES ('" + itemName + "', '" + itemPrice + "')";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteItem(String itemId) {
        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM inventories WHERE id_item = '" + itemId + "'";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }

        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM items WHERE id = '" + itemId + "'";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
