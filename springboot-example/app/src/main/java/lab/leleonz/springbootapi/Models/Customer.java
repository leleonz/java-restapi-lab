package lab.leleonz.springbootapi.Models;

import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lab.leleonz.springbootapi.Exceptions.EmptyNameException;
import lab.leleonz.springbootapi.Exceptions.InvalidEmailException;

@Table(name = "Customers")
@Entity
public class Customer {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    
    @Column(name="First_Name")
    private String firstName;
    
    @Column(name="Last_Name")
    private String lastName;

    @Column(name="Email")
    private String email;

    protected Customer() {}

    public Customer(String firstName, String lastName, String email) {
        validateName(firstName, "first name");
        validateName(lastName, "last name");
        validateEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "first name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateName(lastName, "last name");
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer customer = (Customer) obj;
		return Objects.equals(this.id, customer.id) && Objects.equals(this.firstName, customer.firstName)
                && Objects.equals(this.lastName, customer.lastName) && Objects.equals(this.email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.email);
    }

    @Override
    public String toString() {
        return String.format("Customer id: %d ; Name: %s ; Email: %s", this.id, getFullName(), this.email);
    }

    private void validateName(String name, String nameChoice) {
        if (name == null || name.trim().isEmpty()) throw new EmptyNameException(String.format("Customer's %s cannot be empty", nameChoice));
    }

    private void validateEmail(String email) {
        if(email == null || !Pattern.compile(EMAIL_REGEX).matcher(email).matches()) throw new InvalidEmailException();
    }

}