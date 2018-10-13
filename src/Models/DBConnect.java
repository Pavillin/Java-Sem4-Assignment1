package Models;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String userName = "root";
    private static String password = "";
    //Fritz59!

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

    public static void insertContactIntoDB(Contact newContact) throws SQLException {
        Connection conn = null;
        //prevent sql injection instead of using normal statement
        PreparedStatement ps = null;
        try{
            //1. Connect to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaAssignment1?useSSL=false",userName,password);
            //2. create SQL statement
            String sql = "INSERT INTO contacts (first_name, last_name, birthday, address, phone, image) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            //3. create a prepared statement
            ps = conn.prepareStatement(sql);
            //4. bind params
            ps.setString(1, newContact.getFirstName());
            ps.setString(2, newContact.getLastName());
            ps.setString(3, newContact.getBirthday().toString());
            ps.setString(4, newContact.getAddress());
            ps.setString(5, newContact.getPhone());
            ps.setString(6, newContact.getImage());
            //5. execute and update
            ps.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
        } finally {
            if(conn != null){
                conn.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }
}
