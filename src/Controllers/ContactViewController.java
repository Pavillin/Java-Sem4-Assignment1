package Controllers;

import Models.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactViewController implements Initializable {
    @FXML private ImageView imageView;
    @FXML private TextField first_nameTextField;
    @FXML private TextField last_nameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField phoneTextField;
    @FXML private DatePicker birthdayDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelButtonPushed(ActionEvent sceneChange) throws IOException {
        SceneChanger.changeScenes(sceneChange, "../Views/ContactTableView.fxml", "Contacts");
    }
}
