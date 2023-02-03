package org.istore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;

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

}
