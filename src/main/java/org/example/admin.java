package org.example;

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
}
