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
    String userEmail;
    String userId;
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
            if(resultSetCheckRole.next()){
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
    public boolean setAdmin(String userEmailToSetAdmin) {
        try {
            if(!checkIfAdmin()){
                return false;
            }
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET role = 'admin' WHERE email = '" + userEmailToSetAdmin + "'";
            statement.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
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
    private boolean checkIfAdmin(){
        try{
            Statement statementEmail = connect.createStatement();
            ResultSet resultSetCheckRole;
            resultSetCheckRole = statementEmail.executeQuery("SELECT role FROM users WHERE email ='" + userEmail + "'");
            if (resultSetCheckRole.next()) {
                if (!resultSetCheckRole.getString("role").equals("admin")) {
                    return false;
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
    public void changePasswordFromVisitor(String visitorEmail, String newVisitorPassword){
        //A voir pour ne pas changer l'id pour le mail (plus simple, moins sécu)
        try{
            if (checkIfAdmin()){
                Statement statement = connect.createStatement();
                String sql = "UPDATE users SET password = '"+encryptPassword(newVisitorPassword)+"' WHERE email = '"+visitorEmail+"'";
                statement.executeUpdate(sql);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean changeEmailFromVisitor(String visitorEmail, String newVisitorEmail){
        try{
            if(!checkIfAdmin()){
                return false;
            }
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET email = '"+newVisitorEmail+"' WHERE email = '"+visitorEmail+"'";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
}


