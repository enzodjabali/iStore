package org.istore;
import java.sql.Statement;
public class admin extends crud{
    public void changePasswordFromVisitor(String visitorEmail, String newVisitorPassword){
        //A voir pour ne pas changer l'id pour le mail (plus simple, moins sécu)
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET password = '"+encryptPassword(newVisitorPassword)+"' WHERE email = '"+visitorEmail+"'";
            statement.executeUpdate(sql);

        } catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean changeEmailFromVisitor(String visitorEmail, String newVisitorEmail){
        try{
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET email = '"+newVisitorEmail+"' WHERE email = '"+visitorEmail+"'";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
    public boolean whiteListUser(String userEmailToWhiteList){
        try{
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
            Statement statement = connect.createStatement();
            String sql = "UPDATE users SET role = 'admin' WHERE email = '" + userEmailToSetAdmin + "'";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public void deleteEmployee(String email){
        try{
            Statement statement = connect.createStatement();
            String sql = "DELETE FROM users WHERE email = '"+email+"'";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}