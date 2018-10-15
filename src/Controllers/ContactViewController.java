package Controllers;

import Models.Contact;
import Models.DBConnect;
import Models.SceneChanger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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

    private File imageFile;
    private boolean imageFileChanged;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageFileChanged = false;

        try{
            imageFile = new File("./src/Views/Images/Default.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
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
            Contact newContact;
            if (imageFileChanged){
                newContact = new Contact(
                        0,
                        first_nameTextField.getText(),
                        last_nameTextField.getText(),
                        addressTextField.getText(),
                        phoneTextField.getText(),
                        birthdayDatePicker.getValue(),
                        imageFile
                );
            }else{
                newContact = new Contact(
                        0,
                        first_nameTextField.getText(),
                        last_nameTextField.getText(),
                        addressTextField.getText(),
                        phoneTextField.getText(),
                        birthdayDatePicker.getValue()
                );
            }

            messageLabel.setText("");
            DBConnect.insertContactIntoDB(newContact);
            SceneChanger.changeScenes(sceneChange, "../Views/ContactTableView.fxml", "Contacts");
        } catch ( Exception e){
            messageLabel.setText(e.getMessage());
        }
    }

    /**
     * When the 'Choose Image' button is pressed a filechooser object will be launched so the user can select an image from their computer
     */
    public void chooseImageButtonPushed(ActionEvent chooseImage){
        Stage stage = (Stage)((Node)chooseImage.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        //filter for .jpg and .png
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

        //set to users pictures directory
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);
        //if you cant get to pictures directory go to the users home
        if (!userDirectory.canRead()){
            userDirectory = new File(System.getProperty("user.home"));
        }

        fileChooser.setInitialDirectory(userDirectory);
        //open the file dialog window
        File tmpImageFile = fileChooser.showOpenDialog(stage);
        if (tmpImageFile != null){
            imageFile = tmpImageFile;
            //update the imageView with the new image
            if (imageFile.isFile()){
                try{
                    BufferedImage bufferedImage = ImageIO.read(imageFile);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(img);
                    imageFileChanged = true;
                } catch(IOException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
