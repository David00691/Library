package pl.java.dawid.library.database;
import java.sql.*;
import java.sql.Connection;

public class Provider {
    private static final String url = "jdbc:mysql://localhost:3306/bookdb";

    private static final String user = "root";
    private static final String password = "Root123456789";
    public static Connection conn;
    private static final Provider instance = new Provider();

    public Provider() {
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static Provider getInstance() { return instance; }


}