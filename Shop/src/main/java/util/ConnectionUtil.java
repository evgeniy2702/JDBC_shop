package util;

import java.sql.*;

public class ConnectionUtil {

    public static final String URL = "jdbc:mysql://localhost:3306/shop";
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static CallableStatement callableStatement = null;


    public static void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL,"root", "root");
        if (connection == null)
            System.err.println("Нет соединения с БД!");
        else
            System.out.println("Cоединения с БД установлено!");
    }

    public static void closePreparedStatment() {
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("PreparedStatement is absent!");
        }
    }

    public static void closeCallableStatment() {
        if(callableStatement!=null){
            try {
                callableStatement.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("CallableStatement is absent!");
        }
    }
    public static void closeConnection() {
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Connection is absent!");
        }
    }
}
