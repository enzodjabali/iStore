package org.istore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    DBManager database = new DBManager();
    Connection connect = database.dbconnect();
    String userEmail;
    String userId;

    public String getId() {
        return userId;
    }

    public String getPseudo() {
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT pseudo FROM users WHERE id = " + userId);
            if(resultSet.next()) {
                return resultSet.getString("pseudo");
            }
            return null;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean isWhitelisted(String userEmail) {
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT whitelisted FROM users WHERE email = '" + userEmail + "'");
            if(resultSet.next()) {
                if (resultSet.getString("whitelisted").equals("1")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean userConnect(String emailToCheck, String pwdToCheck) {
        if (emailToCheck.isEmpty() || pwdToCheck.isEmpty()) {
            return false;
        }

        // Voir pour récupérer Role et Pseudo de la requete
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email ='"+emailToCheck+"' AND password ='"+encryptPassword(pwdToCheck)+"'");
            if(resultSet.next()) {
                userId = resultSet.getString("id");
                userEmail = resultSet.getString("email");
                return true;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public String encryptPassword(String pwdToCheck) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwdToCheck.getBytes(StandardCharsets.UTF_8));
            pwdToCheck = new String(hash, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println(e);
        }
        return pwdToCheck;
    }

    public boolean userRegister(String pseudo, String email, String password){
        try{
            Statement statementEmail;
            statementEmail = connect.createStatement();
            ResultSet resultSetEmail;

            resultSetEmail = statementEmail.executeQuery("SELECT email FROM users WHERE email ='"+email+"'");
            //check if email already exist
            int counter = 0;
            while(resultSetEmail.next()){
                counter++;
            }
            if(counter>0){
                System.out.println("email already used");
                return false;
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String pwd = new String(hash, StandardCharsets.UTF_8);
            //Requete
            Statement statement = connect.createStatement();
            ResultSet resultSet;
            String sql = "INSERT INTO users (pseudo, email, password) VALUES ('"+pseudo+"', '"+email+"', '"+pwd+"')";
            statement.executeUpdate(sql);
            return true;

        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean userUpdate(String userId, String newPseudo, String newEmail, String newPassword){
        try{
            Statement statementEmail;
            statementEmail = connect.createStatement();
            ResultSet resultSetEmail;

            resultSetEmail = statementEmail.executeQuery("SELECT email FROM users WHERE email ='" + newEmail + "'");
            int counter = 0;
            while(resultSetEmail.next()){
                counter++;
            }
            if(counter>0){
                System.out.println("email already used");
                return false;
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(newPassword.getBytes(StandardCharsets.UTF_8));
            String cryptedNewPassword = new String(hash, StandardCharsets.UTF_8);

            Statement statement = connect.createStatement();
            String sql = "UPDATE users\n" +
                    "SET pseudo = '" + newPseudo + "',\n" +
                    " email = '" + newEmail + "',\n" +
                    " password = '" + cryptedNewPassword + "'\n" +
                    " WHERE id = " + userId + "";
            statement.executeUpdate(sql);

            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean whitelistUser(String whitelist, String userToWhitelist){
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET whitelisted = '" + whitelist + "' WHERE id = '" + userToWhitelist + "'";
            statement.executeUpdate(sql);
            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean updateRole(String userId, String newRole){
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET `role` = '" + newRole + "' WHERE id = " + userId + "";
            statement.executeUpdate(sql);
            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUser(String userId){
        try {
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM users WHERE id = '" + userId + "'";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
