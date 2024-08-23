package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection con=null;

    public static Connection getConnection() {
        try {
            if (con==null){
                Properties prop= loadProperties();
                String url = prop.getProperty("dburl");
                con= DriverManager.getConnection(url,prop);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return con;
    }

    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("db.properties")){
        Properties prop = new Properties();
        prop.load(fs);
        return prop;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static void closeConnection(Connection con){
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeStatement(Statement st){
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
