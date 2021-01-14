/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.DAL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author xuanluan
 */
public class ConnectSQLServer {

    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=FLIGHT;"
            + "user=sa;password=123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL); 
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
