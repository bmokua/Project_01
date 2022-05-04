package dev.mokua.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){
        try{
           String secureConnection = System.getenv("DATABASE_URL");
           Connection conn = DriverManager.getConnection(secureConnection);
           return conn;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
