package Models;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String userName = "root";
    private static String password = "";

    /**
     * Pull data from database and store into contact objects and return an arraylist with all the contacts
     * @return
     * @throws SQLException
     */
    public static ArrayList<Contact> getContacts() throws SQLException, IOException {
        ArrayList<Contact> Contacts = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaAssignment1?useSSL=false",userName, password);

            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM contacts");

            while (resultSet.next()){
                Contact newContact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getDate("birthday").toLocalDate());
                        newContact.setImageFile(new File(resultSet.getString("imageFile")));
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

    /**
     * Insert a contact object into the database using a prepared statement
     * @param newContact
     * @throws SQLException
     */
    public static void insertContactIntoDB(Contact newContact) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaAssignment1?useSSL=false",userName,password);

            String sql = "INSERT INTO contacts (first_name, last_name, birthday, address, phone, imageFile) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            ps = conn.prepareStatement(sql);
            ps.setString(1, newContact.getFirstName());
            ps.setString(2, newContact.getLastName());
            ps.setString(3, newContact.getBirthday().toString());
            ps.setString(4, newContact.getAddress());
            ps.setString(5, newContact.getPhone());
            ps.setString(6, newContact.getImageFile().getName());
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
