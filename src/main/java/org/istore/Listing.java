package org.istore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Listing {

    DBManager database = new DBManager();
    Connection connect = database.dbconnect();

    public ArrayList<ArrayList<String>> getStoreList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM stores");

            //Extact result from ResultSet rs
            while (resultSet.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(resultSet.getString("id"));
                item.add(resultSet.getString("name"));
                items.add(item);
            }
            // close ResultSet rs
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //return false;
        return items;
    }

    public ArrayList<ArrayList<String>> getStoreItems(int storeId) {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT items.id, items.name, items.price, stores.name\n" +
                    "FROM stores\n" +
                    "INNER JOIN inventories ON stores.id = inventories.id_store\n" +
                    "INNER JOIN items ON inventories.id_item = items.id\n" +
                    "WHERE stores.id = "+ storeId +"\n" +
                    "ORDER BY items.id;");

            while (resultSet.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(resultSet.getString("items.id"));
                item.add(resultSet.getString("items.name"));
                item.add(resultSet.getString("items.price"));
                item.add(resultSet.getString("stores.name"));
                items.add(item);
            }
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }

    public ArrayList<ArrayList<String>> getUserList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM users");

            //Extact result from ResultSet rs
            while (resultSet.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(resultSet.getString("id"));
                item.add(resultSet.getString("email"));
                item.add(resultSet.getString("pseudo"));
                item.add(resultSet.getString("role"));
                item.add(resultSet.getString("whitelisted"));
                items.add(item);
            }
            // close ResultSet rs
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //return false;
        return items;
    }

    public ArrayList<ArrayList<String>> getItemList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM items");

            while (resultSet.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(resultSet.getString("id"));
                item.add(resultSet.getString("name"));
                item.add(resultSet.getString("price"));
                items.add(item);
            }
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }
}
