package test;
import test.JDBCTools;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQL {
    public static void AddOnboarding(ArrayList<ArrayList<String>> ans) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SQL";
        for(ArrayList<String>temp:ans){
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, temp.get(0));
                preparedStatement.setString(2, temp.get(1));
                preparedStatement.setString(3, temp.get(2));
                preparedStatement.setString(4, temp.get(3));
                preparedStatement.setString(5, temp.get(4));
                preparedStatement.setString(6, temp.get(5));
                preparedStatement.setString(7, temp.get(6));
                preparedStatement.setString(8, temp.get(7));
                preparedStatement.setString(9, temp.get(8));
                preparedStatement.setString(10, temp.get(9));
                preparedStatement.setString(11, temp.get(10));
                preparedStatement.setString(12, temp.get(11));


                java.sql.Timestamp stp=new java.sql.Timestamp(new java.util.Date().getTime());
                preparedStatement.setTimestamp(13, stp);
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        JDBCTools.release(connection,preparedStatement,resultSet);
    }
    public static void AddTermination(ArrayList<ArrayList<String>> ans) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SQL";
        for(ArrayList<String>temp:ans){
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, temp.get(0));
                preparedStatement.setString(2, temp.get(1));
                preparedStatement.setString(3, temp.get(2));
                preparedStatement.setString(4, temp.get(3));
                preparedStatement.setString(5, temp.get(4));
                preparedStatement.setString(6, temp.get(5));
                preparedStatement.setString(7, temp.get(6));
                preparedStatement.setString(8, temp.get(7));
                preparedStatement.setString(9, temp.get(8));
                preparedStatement.setString(10, temp.get(9));
                preparedStatement.setString(11, temp.get(10));
                preparedStatement.setString(12, temp.get(11));
                //java.sql.Date date= new java.sql.Date(System.currentTimeMillis());
                java.sql.Timestamp stp=new java.sql.Timestamp(new java.util.Date().getTime());
                preparedStatement.setTimestamp(13, stp);
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        JDBCTools.release(connection,preparedStatement,resultSet);
    }

}
