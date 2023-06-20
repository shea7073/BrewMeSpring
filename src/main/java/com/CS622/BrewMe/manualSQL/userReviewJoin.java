package com.CS622.BrewMe.manualSQL;

import java.sql.*;

public class userReviewJoin {


    public static void main(String[] args) {

        String connectionString = "jdbc:mysql://localhost:3306/BrewMe?user=root";

        try (Connection connection = DriverManager.getConnection(connectionString)){
            System.out.println("Connection Successful");
            String sql = "SELECT * FROM users, post WHERE users.username = post.author";
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println("Query successful");
                while (resultSet.next()){
                    System.out.println(resultSet);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



