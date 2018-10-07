package Models;

import java.util.Date;

public class Contact {
    private String firstName, lastName, address, phone;
    private Date birthday;

    public Contact(String firstName, String lastName, String address, String phone, Date birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhone(phone);
        setBirthday(birthday);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
