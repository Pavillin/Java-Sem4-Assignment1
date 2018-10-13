package Models;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String userName = "root";
    private static String password = "";

    public static ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> Contacts = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaAssignment1?useSSL=false",userName, password);

            //2. create a statement object
            statement = conn.createStatement();

            //3. create and execute the query
            resultSet = statement.executeQuery("SELECT * FROM contacts");

            //4. loop over the results and add to the arraylist
            while (resultSet.next()){
                Contact newContact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getString("image"));
                Contacts.add(newContact);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        finally{
            if(conn != null){
                conn.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return Contacts;
    }
}
