package com.CS622.BrewMe.manualSQL;

import org.checkerframework.checker.units.qual.A;

import java.sql.*;
import java.util.ArrayList;

public class averageReviewRating {

    public static void main(String[] args) {

    }

    public static ArrayList<Object[]> ratingsByAuthor() throws SQLException {

        ArrayList<Object[]> data = new ArrayList<>();

        String connectionString = "jdbc:mysql://localhost:3306/BrewMe?user=root";

        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Connection Successful");
            String sql = "SELECT AVG(rating), author, COUNT(rating) FROM POST GROUP BY author";
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(sql);
                System.out.println("Query successful");

                while (resultSet.next()){
                    Object[] temp = new Object[3];
                    temp[0] = resultSet.getFloat(1);
                    temp[1] = resultSet.getString(2);
                    temp[2] = resultSet.getString(3);
                    data.add(temp);

                }
                //connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}