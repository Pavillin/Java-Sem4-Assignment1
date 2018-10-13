package Models;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Contact {
    private String firstName, lastName, address, phone;
    private LocalDate birthday;
    private int id;
    private Image image;

    public Contact(int id, String firstName, String lastName, String address, String phone, LocalDate birthday, Image image) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhone(phone);
        setBirthday(birthday);
        setImage(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()){
            throw new IllegalArgumentException("First name cannot be empty");
        } else{
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()){
            throw new IllegalArgumentException("Last name cannot be empty");
        } else{
            this.lastName = lastName;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.isEmpty()){
            throw new IllegalArgumentException("Address cannot be empty");
        } else{
            this.address = address;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.isEmpty()){
            throw new IllegalArgumentException("Phone Number cannot be empty");
        } else{
            this.phone = phone;
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday){
        if (birthday.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Birthday cannot be in the future");
        } else if (birthday.isEqual(LocalDate.now())){
            throw new IllegalArgumentException("You were not born today, and neither was I");
        } else{
            this.birthday = birthday;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        if(image.equals("")){
            image = new Image("default.jpg");
        } else{
            this.image = image;
        }
    }
}
