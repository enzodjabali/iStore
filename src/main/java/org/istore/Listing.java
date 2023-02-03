package org.istore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import io.github.cdimascio.dotenv.Dotenv;

public class Listing {

    DBManager database = new DBManager();
    Connection connect = database.dbconnect();

    public void getStoreList() {

        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM stores");

            //Extact result from ResultSet rs
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
            }
            // close ResultSet rs
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //return false;
    }

}
