package Controllers;

import Models.Contact;
import Models.DBConnect;
import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactTableViewController implements Initializable {
    @FXML private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, Integer> idColumn;
    @FXML private TableColumn<Contact, String> first_nameColumn;
    @FXML private TableColumn<Contact, String> last_nameColumn;
    @FXML private TableColumn<Contact, String> addressColumn;
    @FXML private TableColumn<Contact, String> phoneColumn;

    /**
     * Retrieve contact data from database and add the data to the table
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));

        try {
            tableView.getItems().addAll(DBConnect.getContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If user clicks 'Create Contact' button, switch to the 'Create a Contact' page
     * @param sceneChange
     * @throws IOException
     */
    public void changeToContactScene(ActionEvent sceneChange) throws IOException {
        SceneChanger.changeScenes(sceneChange, "../Views/ContactView.fxml", "Create a Contact");
    }
}
