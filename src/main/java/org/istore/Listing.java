package org.istore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Listing {

    DBManager database = new DBManager();
    Connection connect = database.dbconnect();

    public ArrayList<ArrayList<String>> getStoreList(String userId, Boolean isAdmin) {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            if(!isAdmin){
                resultSet = statement.executeQuery("" +
                        "SELECT stores.id as 'id', stores.name as 'name' FROM stores_access\n" +
                        "LEFT JOIN stores ON stores_access.id_store = stores.id\n" +
                        "WHERE stores_access.id_user = "+userId+"");
            } else {
                resultSet = statement.executeQuery("SELECT id, name FROM stores");
            }
            // Récupérer id user, id des stores.
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

    public ArrayList<ArrayList<String>> getStoreItems(int storeId, String userId, boolean isAdmin) {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statementVerifyPrivileges;
            statementVerifyPrivileges = connect.createStatement();
            ResultSet resultSetVerifyPrivileges;
            String and = "AND stores_access.id_user = "+userId+"\n";
            String sql = "SELECT stores_access.id_store, stores_access.id_user \n" +
                    "FROM stores_access\n" +
                    "WHERE stores_access.id_store = "+storeId+"\n";
            if(!isAdmin){
                sql += and;
            }
            resultSetVerifyPrivileges = statementVerifyPrivileges.executeQuery(sql);

            if (!resultSetVerifyPrivileges.next()) {
                System.out.println("Nothing found");
                return new ArrayList<>();
            }
            resultSetVerifyPrivileges.close();

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

    public ArrayList<ArrayList<String>> getUserStoreList(String storeId) {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT *\n" +
                    "FROM users\n" +
                    "INNER JOIN stores_access ON users.id = stores_access.id_user\n" +
                    "INNER JOIN stores ON stores_access.id_store = stores.id\n" +
                    "WHERE stores_access.id_store = " + storeId + ";");

            while (resultSet.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(resultSet.getString("id"));
                item.add(resultSet.getString("email"));
                item.add(resultSet.getString("pseudo"));
                item.add(resultSet.getString("role"));
                item.add(resultSet.getString("whitelisted"));
                item.add(resultSet.getString("name"));
                items.add(item);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }

    public ArrayList<ArrayList<String>> getItemList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM items ");

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
