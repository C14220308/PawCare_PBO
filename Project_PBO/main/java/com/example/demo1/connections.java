package com.example.demo1;
import java.sql.*;

public class connections {
    static String url = "jdbc:mysql://localhost:3306/pet_shop?&serverTimezone=UTC";
    static String user = "root";
    static String password ="";

    public static java.sql.Connection GetConnection() throws SQLException{
        java.sql.Connection con = DriverManager.getConnection(url,user,password);
        return con;
    }
}
