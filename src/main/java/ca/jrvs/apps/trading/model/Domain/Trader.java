package ca.jrvs.apps.trading.model.Domain;

import java.time.LocalDate;
import java.util.Date;

public class Trader implements Entity<Integer> {

    private Integer id;
    private String firstName;
    private String lastName  ;
    private Date dob;
    private String country;
    private String email;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer s) {
        this.id=s;

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
