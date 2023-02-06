package org.istore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StoreManager extends Admin {
    DBManager database = new DBManager();
    Connection connect = database.dbconnect();
    public boolean createStore(String storeName) {
        try {
            Statement statement = connect.createStatement();
            String sql = "INSERT INTO stores (name) VALUES ('"+storeName+"')";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean deleteStore(String storeId) {
        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM inventories WHERE id_store = ('" + storeId + "')";
            statement.executeUpdate(sql);

            Statement statementDelStoresAccess = connect.createStatement();
            String sqlDelStoreAccess = "DELETE FROM stores_access WHERE id_store = ('" + storeId + "')";
            statementDelStoresAccess.executeUpdate(sqlDelStoreAccess);

            Statement statementDel = connect.createStatement();
            String sqlDel = "DELETE FROM stores WHERE id = ('" + storeId + "')";
            statementDel.executeUpdate(sqlDel);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean renameStore(String newName, String storeId) {
        try {
            Statement statement = connect.createStatement();
            String sql = "UPDATE stores SET name = '" + newName + "' WHERE id = '" + storeId + "'";
            statement.executeUpdate(sql);
            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public void createItem(String itemName) {
        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet;
            String sql = "INSERT INTO items (name) VALUES ('" + itemName + "')";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean addItemToStore(String itemId, String storeId) {
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT id_item FROM inventories WHERE id_store = " + storeId + "");
            while(resultSet.next()) {
                //Afficher ici
                if(itemId.equals(resultSet.getString("id_item"))){
                    return false;
                }
            }
            Statement statementInsert = connect.createStatement();
            String sql = "INSERT INTO inventories (id_item, id_store) VALUES ('" + itemId + "', '" + storeId + "')";
            statementInsert.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
    public boolean deleteItemFromStore(String itemId, String storeId) {
        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM inventories WHERE id_item = '" + itemId + "' AND id_store = '" + storeId + "'";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean addUserToStore(String idUser, String idStore) {
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT id_user FROM stores_access WHERE id_store = " + idStore + " AND id_user= " + idUser);
            if(resultSet.next()) {
                return false;
            }
            Statement statementInsert = connect.createStatement();
            String sql = "INSERT INTO stores_access (id_store, id_user) VALUES ('" + idStore + "', '" + idUser + "')";
            statementInsert.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public boolean deleteUserFromStore(String idUser, String idStore) {
        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM stores_access WHERE id_user = '" + idUser + "' AND id_store = '" + idStore + "'";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
