package org.example;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import io.github.cdimascio.dotenv.Dotenv;

public class crud {
    Connection connect;
    String userEmail ="";
    Dotenv dotenv = Dotenv.configure().load();

    public void dbconnect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    String.format(dotenv.get("DATABASE_URL")),
                    String.format(dotenv.get("DATABASE_USER")),
                    String.format(dotenv.get("DATABASE_PASSWORD"))
            );
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public void dbdisconnect(){
        try{
            connect.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean userConnect(String emailToCheck, String pwdToCheck) {
        // Voir pour récupérer Role et Pseudo de la requete
        try {
            Statement statement;
            statement = connect.createStatement();
            ResultSet resultSet;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwdToCheck.getBytes(StandardCharsets.UTF_8));
            String passwordToCheck = new String(hash, StandardCharsets.UTF_8);

            resultSet = statement.executeQuery("SELECT * FROM users WHERE email ='"+emailToCheck+"' AND password ='"+passwordToCheck+"'");
            String email = "";
            String password = "";
            int counter = 0;
            while (resultSet.next()) {
                counter++;
                email = resultSet.getString("email");
                password = resultSet.getString("password");
            }
            resultSet.close();
            statement.close();
            if ((emailToCheck).equals(email) && passwordToCheck.equals(password)){
                userEmail = emailToCheck;
                return true;
            } else {
                return false;
            }
        } catch (Exception exception){
            System.out.println(exception);
        }
        return false;
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
                System.out.println("email already in use");
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
    public boolean whiteListUser(String userEmailToWhiteList){
        try{
            Statement statementEmail = connect.createStatement();
            ResultSet resultSetCheckRole;

            resultSetCheckRole = statementEmail.executeQuery("SELECT role FROM users WHERE email ='"+userEmail+"'");
            while(resultSetCheckRole.next()){
                //Vérifie si la requête vient d'un admin
                if(!resultSetCheckRole.getString("role").equals("admin")){
                    return false;
                }
            }
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET whitelisted = 1 WHERE email = '"+userEmailToWhiteList+"'";
            statement.executeUpdate(sql);

        } catch (Exception e){
            System.out.println(e);
        }
    return true;
    }
    public boolean setAdmin(String userToSetAdmin) {
        try {
            Statement statementEmail = connect.createStatement();
            ResultSet resultSetCheckRole;

            resultSetCheckRole = statementEmail.executeQuery("SELECT role FROM users WHERE email ='" + userEmail + "'");
            while (resultSetCheckRole.next()) {
                //Vérifie si la requête vient d'un admin
                if (!resultSetCheckRole.getString("role").equals("admin")) {
                    return false;
                }
            }
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET role = 'admin' WHERE email = '" + userToSetAdmin + "'";
            statement.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
}

