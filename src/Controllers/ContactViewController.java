package Controllers;

import Models.Contact;
import Models.DBConnect;
import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactViewController implements Initializable {
    @FXML private ImageView imageView;
    @FXML private TextField first_nameTextField;
    @FXML private TextField last_nameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField phoneTextField;
    @FXML private DatePicker birthdayDatePicker;
    @FXML private Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * If user clicks the 'Cancel' button on the Create a Contact page, return them to the Contact List page
     * @param sceneChange
     * @throws IOException
     */
    public void cancelButtonPushed(ActionEvent sceneChange) throws IOException {
        SceneChanger.changeScenes(sceneChange, "../Views/ContactTableView.fxml", "Contacts");
    }

    /**
     * When the 'Save' button is pushed we'll attempt to save the contact to the database and display any validation errors
     * @param sceneChange
     */
    @FXML
    public void createContactButtonPushed(ActionEvent sceneChange) {
        try {
            Contact newContact = new Contact(
                    0,
                    first_nameTextField.getText(),
                    last_nameTextField.getText(),
                    addressTextField.getText(),
                    phoneTextField.getText(),
                    birthdayDatePicker.getValue(),
                    "Default.jpg"
            );
            messageLabel.setText("");
            DBConnect.insertContactIntoDB(newContact);
            SceneChanger.changeScenes(sceneChange, "../Views/ContactTableView.fxml", "Contacts");
        } catch ( Exception e){
            messageLabel.setText(e.getMessage());
        }
    }
}
