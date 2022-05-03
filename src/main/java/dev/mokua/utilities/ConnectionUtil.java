package dev.mokua.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){
        try{
           //String secureConnection = System.getenv("DatabaseUrl");
            Connection conn = DriverManager.getConnection( "jdbc:postgresql://mokua-db.cyvhnxmsrinx.us-east-1." +
                    "rds.amazonaws.com/expense?user=postgres&password=moseti123");
            //Connection conn = DriverManager.getConnection(secureConnection);
            return conn;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
