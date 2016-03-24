package speedyBet.service.dao.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection conn;

    // JDBC connection 
    public static Connection getconnection() throws ClassNotFoundException {
        String DB = "sql8109923";
        String User = "sql8109923";
        String Pass = "DpGZVTr2vs";
        String Host = "sql8.freemysqlhosting.net";
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/speedybet", "root", "secret_root_password");
        } catch (SQLException ex) {
            System.out.println("FAILED TO CONNECT TO DATABASE");
        }
        return conn;

    }

}
