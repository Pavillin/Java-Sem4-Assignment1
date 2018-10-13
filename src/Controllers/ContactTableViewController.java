package Controllers;

import Models.Contact;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactTableViewController implements Initializable {
    @FXML private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, Integer> idColumn;
    @FXML private TableColumn<Contact, String> first_nameColumn;
    @FXML private TableColumn<Contact, String> last_nameColumn;
    @FXML private TableColumn<Contact, String> addressColumn;
    @FXML private TableColumn<Contact, String> phoneColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
