package org.istore;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    DBManager database = new DBManager();
    Connection connect = database.dbconnect();
    String userEmail;
    String userId;

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
                System.out.println("email already use");
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
    public void changeEmail(String newEmail){
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET email = '"+newEmail+"' WHERE id = '"+userId+"'";
            statement.executeUpdate(sql);
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void changePassword(String newPassword){
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET `password` = '"+encryptPassword(newPassword)+"' WHERE id = "+userId+"";
            statement.executeUpdate(sql);
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void getInfosFromUsers(){
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT pseudo, email, role FROM users");
            while(resultSet.next()) {
                //Afficher ici
                String pseudo = resultSet.getString("pseudo");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}


