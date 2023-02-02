package org.istore;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    Connection connect;
    Dotenv dotenv = Dotenv.configure().load();

    public Connection dbconnect() {
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

        return connect;
    }
    public void dbdisconnect(){
        try{
            connect.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
