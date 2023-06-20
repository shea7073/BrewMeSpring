package com.CS622.BrewMe.manualSQL;
import org.apache.tomcat.util.security.Escape;

import java.sql.*;
import java.util.ArrayList;

public class breweryInsert {


    public static void main(String[] args) {

        ArrayList<Object[]> brewerData = new ArrayList<>();

        Object[] brewer1 = new Object[]{1, "Night Shift", "Everett, MA", 7811234567L, 1999};
        brewerData.add(brewer1);

        Object[] brewer2 = new Object[]{2, "Clown Shoes", "Boston, MA", 6171234567L, 2009};
        brewerData.add(brewer2);

        Object[] brewer3 = new Object[]{3, "Lagunitas", "Petaluma, CA", 3395556767L, 1993};
        brewerData.add(brewer3);

        Object[] brewer4 = new Object[]{4, "Sam Adams", "Boston, MA", 6175559090L, 1984};
        brewerData.add(brewer4);

        Object[] brewer5 = new Object[]{5, "Harpoon Brewing", "Seaport, MA", 6175554321L, 1986};
        brewerData.add(brewer5);


        String insertString = "INSERT INTO brewery (brewerId, name, location, phoneNum, established) VALUES (?, ?, ?, ?, ?)";

        String connectionString = "jdbc:mysql://localhost:3306/BrewMe?user=root";
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            for (Object[] arr : brewerData) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertString);
                    preparedStatement.setInt(1, (Integer) arr[0]);
                    preparedStatement.setString(2, (String) arr[1]);
                    preparedStatement.setString(3, (String) arr[2]);
                    preparedStatement.setLong(4, (Long) arr[3]);
                    preparedStatement.setInt(5, (Integer) arr[4]);
                    preparedStatement.executeUpdate();
                }
                catch (Exception e) {
                        System.out.println("Error inserting");
                        e.printStackTrace();
                    }
            }

        }
        catch (Exception e){
            System.out.println("Failure");
        }
    }

}
